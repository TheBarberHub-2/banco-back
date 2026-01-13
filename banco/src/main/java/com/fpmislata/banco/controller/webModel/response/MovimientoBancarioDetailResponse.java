package com.fpmislata.banco.controller.webModel.response;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimientoBancarioDetailResponse(
        TipoMovimientoBancario tipoMovimientoBancario,
        OrigenMovimientoBancario origenMovimientoBancario,
        TarjetaCreditoDetailResponse tarjetaCreditoOrigen,
        LocalDateTime fecha,
        BigDecimal importe,
        String concepto) {
}
