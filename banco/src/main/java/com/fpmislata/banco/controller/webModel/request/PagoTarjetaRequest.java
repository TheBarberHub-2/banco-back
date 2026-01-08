package com.fpmislata.banco.controller.webModel.request;

public record PagoTarjetaRequest(
        AutorizacionRequest autorizacion,
        TarjetaCreditoRequest origen,
        DestinoIbanRequest destino,
        PagoRequest pago) {
    public record DestinoIbanRequest(String iban) {
    }
}
