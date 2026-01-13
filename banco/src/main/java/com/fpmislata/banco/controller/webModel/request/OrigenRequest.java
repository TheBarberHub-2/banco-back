package com.fpmislata.banco.controller.webModel.request;

public record OrigenRequest(
        String numeroTarjeta,
        String fechaCaducidad,
        String cvc,
        String nombreCompleto) {

}
