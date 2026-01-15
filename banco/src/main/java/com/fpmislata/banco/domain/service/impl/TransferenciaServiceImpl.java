package com.fpmislata.banco.domain.service.impl;

import java.time.LocalDateTime;

import com.fpmislata.banco.controller.webModel.request.AutorizacionRequest;
import com.fpmislata.banco.controller.webModel.request.DestinoRequest;
import com.fpmislata.banco.controller.webModel.request.OrigenTransferencia;
import com.fpmislata.banco.controller.webModel.request.PagoRequest;
import com.fpmislata.banco.domain.service.ClienteService;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.TransferenciaService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import com.fpmislata.banco.domain.validation.spring_validator.DtoValidator;
import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import com.fpmislata.banco.exception.BusinessException;

public class TransferenciaServiceImpl implements TransferenciaService {

    private final ClienteService clienteService;

    private final CuentaBancariaService cuentaBancariaService;

    private final MovimientoBancarioService movimientoBancarioService;

    public TransferenciaServiceImpl(ClienteService clienteService,
            CuentaBancariaService cuentaBancariaService, MovimientoBancarioService movimientoBancarioService) {
        this.clienteService = clienteService;
        this.cuentaBancariaService = cuentaBancariaService;
        this.movimientoBancarioService = movimientoBancarioService;
    }

    @Override
    public void transferencia(AutorizacionRequest autorizacionRequest, OrigenTransferencia origenRequest,
            DestinoRequest destinoRequest, PagoRequest pagoRequest) {

        ClienteDto clienteDto = clienteService.getByLogin(autorizacionRequest.login());

        if (!clienteDto.apiToken().equals(autorizacionRequest.api_token())) {
            throw new BusinessException("Autorización incorrecta");
        }

        DtoValidator.validate(origenRequest);

        CuentaBancariaDto cuentaOrigen = cuentaBancariaService.getByIban(origenRequest.iban());

        if (cuentaOrigen.cliente().id() != clienteDto.id()) {
            throw new BusinessException("La cuenta origen no pertenece al usuario");
        }

        DtoValidator.validate(destinoRequest);

        CuentaBancariaDto cuentaDestino = cuentaBancariaService.getByIban(destinoRequest.iban());

        DtoValidator.validate(pagoRequest);

        if (cuentaOrigen.saldo().compareTo(pagoRequest.importe()) < 0) {
            throw new BusinessException("Saldo insuficiente en la cuenta origen");
        }

        MovimientoBancarioDto movimientoOrigen = new MovimientoBancarioDto(
                null,
                cuentaOrigen,
                TipoMovimientoBancario.DEBE,
                OrigenMovimientoBancario.TRANSFERENCIA,
                null,
                LocalDateTime.now(),
                pagoRequest.importe().negate(),
                pagoRequest.concepto());

        MovimientoBancarioDto movimientoDestino = new MovimientoBancarioDto(
                null,
                cuentaDestino,
                TipoMovimientoBancario.HABER,
                OrigenMovimientoBancario.TRANSFERENCIA,
                null,
                LocalDateTime.now(),
                pagoRequest.importe(),
                pagoRequest.concepto());

        movimientoBancarioService.insert(movimientoOrigen);
        cuentaBancariaService.updateSaldo(cuentaOrigen.id(), pagoRequest.importe().negate());
        movimientoBancarioService.insert(movimientoDestino);
        cuentaBancariaService.updateSaldo(cuentaDestino.id(), pagoRequest.importe());
    }
}
