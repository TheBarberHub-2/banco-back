package com.fpmislata.banco.controller.webModel.request;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import java.math.BigDecimal;
import java.util.Date;

public record MovimientoBancarioRequest(
        TipoMovimientoBancario tipoMovimientoBancario,
        OrigenMovimientoBancario origenMovimientoBancario,
        TarjetaCreditoRequest tarjetaCreditoOrigen,
        Date fecha,
        BigDecimal importe,
        String concepto) {
}
