package com.fpmislata.banco.controller.webModel.response;

public record TarjetaCreditoDetailResponse(
        String numeroTarjeta,
        String fechaCaducidad,
        String cvc,
        String nombreCompleto) {
}
