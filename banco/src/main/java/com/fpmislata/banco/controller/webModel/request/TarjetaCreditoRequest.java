package com.fpmislata.banco.controller.webModel.request;

public record TarjetaCreditoRequest(
        String numeroTarjeta,
        String fechaCaducidad,
        String cvc,
        String nombreCompleto) {
}
