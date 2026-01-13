package com.fpmislata.banco.domain.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;

public record MovimientoBancarioEntity(
        Long id,
        CuentaBancariaEntity cuentaBancaria,
        TipoMovimientoBancario tipoMovimientoBancario,
        OrigenMovimientoBancario origenMovimientoBancario,
        TarjetaCreditoEntity tarjetaCreditoOrigen,
        LocalDateTime fecha,
        BigDecimal importe,
        String concepto) {
    public MovimientoBancarioEntity(
            Long id,
            CuentaBancariaEntity cuentaBancaria,
            TipoMovimientoBancario tipoMovimientoBancario,
            OrigenMovimientoBancario origenMovimientoBancario,
            TarjetaCreditoEntity tarjetaCreditoOrigen,
            LocalDateTime fecha,
            BigDecimal importe,
            String concepto) {
        this.id = id;
        this.cuentaBancaria = cuentaBancaria;
        this.tipoMovimientoBancario = tipoMovimientoBancario;
        this.origenMovimientoBancario = origenMovimientoBancario;
        this.tarjetaCreditoOrigen = tarjetaCreditoOrigen;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
    }
}
