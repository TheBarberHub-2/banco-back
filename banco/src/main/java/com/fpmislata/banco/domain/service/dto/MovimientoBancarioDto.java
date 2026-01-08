package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public record MovimientoBancarioDto(
        Long id,
        @NotNull TipoMovimientoBancario tipoMovimientoBancario,
        @NotNull OrigenMovimientoBancario origenMovimientoBancario,
        TarjetaCreditoDto tarjetaCreditoOrigen,
        @NotNull Date fecha,
        @NotNull BigDecimal importe,
        String concepto) {
}
