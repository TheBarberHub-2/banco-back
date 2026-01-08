package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.model.TarjetaCredito;
import com.fpmislata.banco.domain.repository.ClienteRepository;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.domain.service.OperacionBancariaService;
import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import com.fpmislata.banco.exception.BusinessException;
import com.fpmislata.banco.exception.ResourceNotFoundException;
import com.fpmislata.banco.persistence.entity.CuentaBancariaEntity;
import com.fpmislata.banco.persistence.entity.TarjetaCreditoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class OperacionBancariaServiceImpl implements OperacionBancariaService {

    @PersistenceContext
    private EntityManager entityManager;

    private final ClienteRepository clienteRepository;
    private final CuentaBancariaRepository cuentaRepository;
    private final MovimientoBancarioRepository movimientoRepository;

    public OperacionBancariaServiceImpl(ClienteRepository clienteRepository,
            CuentaBancariaRepository cuentaRepository,
            MovimientoBancarioRepository movimientoRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    @Transactional
    public MovimientoBancarioDetailResponse transferencia(TransferenciaRequest request) {
        // 1. Validar Autorizacion
        Cliente cliente = validarAutorizacion(request.autorizacion().login(), request.autorizacion().api_token());

        // 2. Validar IBANs
        validarIban(request.origen().iban());
        validarIban(request.destino().iban());

        // 3. Validar Importe y Concepto
        validarPago(request.pago().importe(), request.pago().concepto());

        // 4. Validar que la cuenta origen pertenece al usuario login
        validarPropiedadCuenta(request.origen().iban(), request.autorizacion().login());

        // 5. Ejecutar Movimiento
        return moverDinero(request.origen().iban(), request.destino().iban(),
                request.pago().importe(), request.pago().concepto(),
                OrigenMovimientoBancario.TRANSFERENCIA, null);
    }

    @Override
    @Transactional
    public MovimientoBancarioDetailResponse pagoTarjeta(PagoTarjetaRequest request) {
        // 1. Validar Autorizacion
        Cliente cliente = validarAutorizacion(request.autorizacion().login(), request.autorizacion().api_token());

        // 2. Validar IBAN destino
        validarIban(request.destino().iban());

        // 3. Validar Importe y Concepto
        validarPago(request.pago().importe(), request.pago().concepto());

        // 4. Validar que la cuenta destino pertenece al usuario login (Regla
        // solicitada)
        validarPropiedadCuenta(request.destino().iban(), request.autorizacion().login());

        // 5. Validar Tarjeta y obtener su cuenta asociada
        TarjetaCreditoEntity tarjetaEntity = validarTarjeta(request.origen().numeroTarjeta(),
                request.origen().fechaCaducidad(),
                request.origen().cvc(),
                request.origen().nombreCompleto());

        // 6. Ejecutar Movimiento desde la cuenta de la tarjeta
        return moverDinero(tarjetaEntity.getCuenta().getIban(), request.destino().iban(),
                request.pago().importe(), request.pago().concepto(),
                OrigenMovimientoBancario.TARJETA_BANCARIA, tarjetaEntity);
    }

    private Cliente validarAutorizacion(String login, String token) {
        return clienteRepository.findByLoginAndApiToken(login, token)
                .orElseThrow(() -> new BusinessException("Autorización fallida para el usuario: " + login));
    }

    private void validarIban(String iban) {
        if (iban == null || !iban.startsWith("ES") || iban.length() < 24) {
            throw new BusinessException("Formato de IBAN inválido: " + iban);
        }
    }

    private void validarPago(BigDecimal importe, String concepto) {
        if (importe == null || importe.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("El importe debe ser positivo");
        }
        if (concepto == null || concepto.trim().length() < 3) {
            throw new BusinessException("El concepto debe tener al menos 3 letras");
        }
    }

    private void validarPropiedadCuenta(String iban, String login) {
        String jpql = "SELECT COUNT(c) FROM CuentaBancariaEntity c WHERE c.iban = :iban AND c.cliente.login = :login";
        Long count = (Long) entityManager.createQuery(jpql)
                .setParameter("iban", iban)
                .setParameter("login", login)
                .getSingleResult();
        if (count == 0) {
            throw new BusinessException("La cuenta " + iban + " no pertenece al usuario " + login);
        }
    }

    private TarjetaCreditoEntity validarTarjeta(String numero, String fecha, String cvc, String nombre) {
        String jpql = "SELECT t FROM TarjetaCreditoEntity t WHERE t.numeroTarjeta = :numero";
        Optional<TarjetaCreditoEntity> optional = entityManager.createQuery(jpql, TarjetaCreditoEntity.class)
                .setParameter("numero", numero)
                .getResultList().stream().findFirst();

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Tarjeta no encontrada: " + numero);
        }

        TarjetaCreditoEntity t = optional.get();
        if (!t.getFechaCaducidad().equals(fecha) || !t.getCvc().equals(cvc)
                || !t.getNombreCompleto().equalsIgnoreCase(nombre)) {
            throw new BusinessException("Los datos de la tarjeta no coinciden");
        }
        return t;
    }

    private MovimientoBancarioDetailResponse moverDinero(String ibanOrigen, String ibanDestino,
            BigDecimal importe, String concepto,
            OrigenMovimientoBancario origen, TarjetaCreditoEntity tarjeta) {

        CuentaBancariaEntity origenEnt = findCuentaByIban(ibanOrigen);
        CuentaBancariaEntity destinoEnt = findCuentaByIban(ibanDestino);

        if (origenEnt.getSaldo().compareTo(importe) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta de origen: " + ibanOrigen);
        }

        // Actualizar saldos
        origenEnt.setSaldo(origenEnt.getSaldo().subtract(importe));
        destinoEnt.setSaldo(destinoEnt.getSaldo().add(importe));

        entityManager.merge(origenEnt);
        entityManager.merge(destinoEnt);

        // Registrar movimiento (simplificado para el ejemplo, usualmente habria 2
        // apuntes o uno con origen/destino)
        // El modelo MovimientoBancario solicitado parece ser un único registro con
        // campos de origen/destino implícitos o a través de la tarjeta

        MovimientoBancario mov = new MovimientoBancario();
        mov.setTipoMovimientoBancario(TipoMovimientoBancario.DEBE); // Es un gasto para el origen
        mov.setOrigenMovimientoBancario(origen);
        mov.setFecha(new Date());
        mov.setImporte(importe);
        mov.setConcepto(concepto);

        if (tarjeta != null) {
            mov.setTarjetaCreditoOrigen(new TarjetaCredito(tarjeta.getNumeroTarjeta(), tarjeta.getFechaCaducidad(),
                    tarjeta.getCvc(), tarjeta.getNombreCompleto()));
        }

        MovimientoBancario saved = movimientoRepository.save(mov);

        return new MovimientoBancarioDetailResponse(
                saved.getId(),
                saved.getTipoMovimientoBancario(),
                saved.getOrigenMovimientoBancario(),
                saved.getTarjetaCreditoOrigen() != null
                        ? new com.fpmislata.banco.controller.webModel.response.TarjetaCreditoDetailResponse(
                                saved.getTarjetaCreditoOrigen().getNumeroTarjeta(),
                                saved.getTarjetaCreditoOrigen().getFechaCaducidad(),
                                saved.getTarjetaCreditoOrigen().getCvc(),
                                saved.getTarjetaCreditoOrigen().getNombreCompleto())
                        : null,
                saved.getFecha(),
                saved.getImporte(),
                saved.getConcepto());
    }

    private CuentaBancariaEntity findCuentaByIban(String iban) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM CuentaBancariaEntity c WHERE c.iban = :iban",
                            CuentaBancariaEntity.class)
                    .setParameter("iban", iban)
                    .getSingleResult();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cuenta no encontrada con IBAN: " + iban);
        }
    }
}
