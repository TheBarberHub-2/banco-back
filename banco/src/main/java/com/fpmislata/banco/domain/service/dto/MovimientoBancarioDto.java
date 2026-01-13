package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoBancarioDto(
                Long id,
                @NotNull CuentaBancariaDto cuentaBancaria,
                @NotNull TipoMovimientoBancario tipoMovimientoBancario,
                @NotNull OrigenMovimientoBancario origenMovimientoBancario,
                TarjetaCreditoDto tarjetaCreditoOrigen,
                @NotNull LocalDateTime fecha,
                @NotNull BigDecimal importe,
                @NotNull @Size(min = 3) String concepto) {

        public MovimientoBancarioDto(Long id, CuentaBancariaDto cuentaBancaria,
                        TipoMovimientoBancario tipoMovimientoBancario,
                        OrigenMovimientoBancario origenMovimientoBancario,
                        TarjetaCreditoDto tarjetaCreditoOrigen, LocalDateTime fecha,
                        BigDecimal importe, String concepto) {
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
