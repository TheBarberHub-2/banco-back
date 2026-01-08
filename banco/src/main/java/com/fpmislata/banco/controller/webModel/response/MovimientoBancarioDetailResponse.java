package com.fpmislata.banco.controller.webModel.response;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import java.math.BigDecimal;
import java.util.Date;

public record MovimientoBancarioDetailResponse(
        Long id,
        TipoMovimientoBancario tipoMovimientoBancario,
        OrigenMovimientoBancario origenMovimientoBancario,
        TarjetaCreditoDetailResponse tarjetaCreditoOrigen,
        Date fecha,
        BigDecimal importe,
        String concepto) {
}
