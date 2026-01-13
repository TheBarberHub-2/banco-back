package com.fpmislata.banco.domain.service.impl;

import java.time.LocalDateTime;

import com.fpmislata.banco.controller.webModel.request.AutorizacionRequest;
import com.fpmislata.banco.controller.webModel.request.DestinoRequest;
import com.fpmislata.banco.controller.webModel.request.OrigenRequest;
import com.fpmislata.banco.controller.webModel.request.PagoRequest;
import com.fpmislata.banco.domain.service.ClienteService;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.PagoTarjetaService;
import com.fpmislata.banco.domain.service.TarjetaCreditoService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;
import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import com.fpmislata.banco.exception.BusinessException;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {

    private final ClienteService clienteService;

    private final TarjetaCreditoService tarjetaCreditoService;

    private final CuentaBancariaService cuentaBancariaService;

    private final MovimientoBancarioService movimientoBancarioService;

    public PagoTarjetaServiceImpl(ClienteService clienteService, TarjetaCreditoService tarjetaCreditoService,
            CuentaBancariaService cuentaBancariaService, MovimientoBancarioService movimientoBancarioService) {
        this.clienteService = clienteService;
        this.tarjetaCreditoService = tarjetaCreditoService;
        this.cuentaBancariaService = cuentaBancariaService;
        this.movimientoBancarioService = movimientoBancarioService;
    }

    @Override
    public void pagoTarjeta(AutorizacionRequest autorizacionRequest, OrigenRequest origenRequest,
            DestinoRequest destinoRequest, PagoRequest pagoRequest) {

        ClienteDto clienteDto = clienteService.getByLogin(autorizacionRequest.login());

        if (!clienteDto.apiToken().equals(autorizacionRequest.api_token())) {
            throw new BusinessException("Autorización incorrecta");
        }

        TarjetaCreditoDto tarjetaCreditoDto = tarjetaCreditoService.findByNumeroTarjeta(origenRequest.numeroTarjeta());

        if (!tarjetaCreditoDto.fechaCaducidad().equals(origenRequest.fechaCaducidad())
                || !tarjetaCreditoDto.cvc().equals(origenRequest.cvc())
                || !tarjetaCreditoDto.nombreCompleto().equals(origenRequest.nombreCompleto())) {

            throw new BusinessException("Datos de la tarjeta incorrectos");
        }

        CuentaBancariaDto cuentaDestino = cuentaBancariaService.getByIban(destinoRequest.iban());

        if (cuentaDestino.cliente().id() != clienteDto.id()) {
            throw new BusinessException("La cuenta destino no pertenece al cliente");
        }

        CuentaBancariaDto cuentaOrigen = tarjetaCreditoDto.cuenta();

        if (cuentaOrigen.saldo().compareTo(pagoRequest.importe()) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta asociada a la tarjeta");
        }

        MovimientoBancarioDto movimientoOrigen = new MovimientoBancarioDto(
                null,
                cuentaOrigen,
                TipoMovimientoBancario.DEBE,
                OrigenMovimientoBancario.TARJETA_BANCARIA,
                tarjetaCreditoDto,
                LocalDateTime.now(),
                pagoRequest.importe().negate(),
                pagoRequest.concepto());

        MovimientoBancarioDto movimientoDestino = new MovimientoBancarioDto(
                null,
                cuentaDestino,
                TipoMovimientoBancario.HABER,
                OrigenMovimientoBancario.TARJETA_BANCARIA,
                tarjetaCreditoDto,
                LocalDateTime.now(),
                pagoRequest.importe(),
                pagoRequest.concepto());

        movimientoBancarioService.insert(movimientoOrigen);
        cuentaBancariaService.updateSaldo(cuentaOrigen.id(), pagoRequest.importe().negate());
        movimientoBancarioService.insert(movimientoDestino);
        cuentaBancariaService.updateSaldo(cuentaDestino.id(), pagoRequest.importe());
    }
}
