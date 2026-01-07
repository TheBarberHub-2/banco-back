package com.fpmislata.banco.controller.webModel.response;

import java.time.LocalDateTime;

public record TransferenciaDetailResponse(
        Long id,
        String cuentaOrigen,
        String cuentaDestino,
        Double monto,
        LocalDateTime fecha,
        String concepto) {
}
