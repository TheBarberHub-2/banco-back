package com.fpmislata.banco.controller.webModel.request;

import java.math.BigDecimal;

public record PagoRequest(
        BigDecimal importe,
        String concepto) {
}
