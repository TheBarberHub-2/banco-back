package com.fpmislata.banco.controller.webModel.request;

public record OrigenPagoTarjetaRequest(
                String numeroTarjeta,
                String fechaCaducidad,
                String cvc,
                String nombreCompleto) {

}
