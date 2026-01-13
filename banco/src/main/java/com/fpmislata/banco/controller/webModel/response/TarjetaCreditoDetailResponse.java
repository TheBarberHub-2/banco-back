package com.fpmislata.banco.controller.webModel.response;

public record TarjetaCreditoDetailResponse(
        Long id,
        String numeroTarjeta,
        String fechaCaducidad,
        String cvc,
        String nombreCompleto) {
}
