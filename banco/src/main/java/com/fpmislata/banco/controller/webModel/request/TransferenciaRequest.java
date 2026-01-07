package com.fpmislata.banco.controller.webModel.request;

public record TransferenciaRequest(
        String cuentaOrigen,
        String cuentaDestino,
        Double monto,
        String concepto) {
}
