package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper INSTANCE;

    private MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovimientoBancarioMapper();
        }
        return INSTANCE;
    }

    public MovimientoBancarioJpaEntity fromEntityToJpa(MovimientoBancarioEntity movimientoBancarioEntity) {
        if (movimientoBancarioEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = movimientoBancarioEntity.cuentaBancaria().cliente();
        ClienteJpaEntity clienteJpaEntity = null;
        if (clienteEntity != null) {
            clienteJpaEntity = new ClienteJpaEntity(
                    clienteEntity.id(),
                    clienteEntity.login(),
                    clienteEntity.password(),
                    clienteEntity.nombre(),
                    clienteEntity.apellido1(),
                    clienteEntity.apellido2(),
                    clienteEntity.dni(),
                    clienteEntity.apiToken(),
                    null);
        }
        CuentaBancariaEntity cuentaBancariaEntity = movimientoBancarioEntity.cuentaBancaria();
        CuentaBancariaJpaEntity cuentaBancariaJpaEntity = null;
        if (cuentaBancariaEntity != null) {
            cuentaBancariaJpaEntity = new CuentaBancariaJpaEntity(
                    cuentaBancariaEntity.id(),
                    clienteJpaEntity,
                    cuentaBancariaEntity.iban(),
                    cuentaBancariaEntity.saldo(),
                    null,
                    null);
        }
        return new MovimientoBancarioJpaEntity(
                movimientoBancarioEntity.id(),
                cuentaBancariaJpaEntity,
                movimientoBancarioEntity.tipoMovimientoBancario(),
                movimientoBancarioEntity.origenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromEntityToJpa(movimientoBancarioEntity.tarjetaCreditoOrigen()),
                movimientoBancarioEntity.fecha(),
                movimientoBancarioEntity.importe(),
                movimientoBancarioEntity.concepto());
    }

    public MovimientoBancarioEntity fromJpaToEntity(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity) {
        if (movimientoBancarioJpaEntity == null) {
            return null;
        }
        ClienteJpaEntity clienteJpaEntity = movimientoBancarioJpaEntity.getCuentaBancaria().getCliente();
        ClienteEntity clienteEntity = null;
        if (clienteJpaEntity != null) {
            clienteEntity = new ClienteEntity(
                    clienteJpaEntity.getId(),
                    clienteJpaEntity.getLogin(),
                    clienteJpaEntity.getPassword(),
                    clienteJpaEntity.getNombre(),
                    clienteJpaEntity.getApellido1(),
                    clienteJpaEntity.getApellido2(),
                    clienteJpaEntity.getDni(),
                    clienteJpaEntity.getApiToken(),
                    null);
        }
        CuentaBancariaJpaEntity cuentaBancariaJpaEntity = movimientoBancarioJpaEntity.getCuentaBancaria();
        CuentaBancariaEntity cuentaBancariaEntity = null;
        if (cuentaBancariaJpaEntity != null) {
            cuentaBancariaEntity = new CuentaBancariaEntity(
                    cuentaBancariaJpaEntity.getId(),
                    clienteEntity,
                    cuentaBancariaJpaEntity.getIban(),
                    cuentaBancariaJpaEntity.getSaldo(),
                    null,
                    null);
        }
        return new MovimientoBancarioEntity(
                movimientoBancarioJpaEntity.getId(),
                cuentaBancariaEntity,
                movimientoBancarioJpaEntity.getTipoMovimientoBancario(),
                movimientoBancarioJpaEntity.getOrigenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance()
                        .fromJpaToEntity(movimientoBancarioJpaEntity.getTarjetaCreditoOrigen()),
                movimientoBancarioJpaEntity.getFecha(),
                movimientoBancarioJpaEntity.getImporte(),
                movimientoBancarioJpaEntity.getConcepto());
    }
}
