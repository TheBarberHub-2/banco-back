package com.fpmislata.banco.controller.webModel.request;

public record PagoTarjetaRequest(
        AutorizacionRequest autorizacion,
        OrigenRequest origen,
        DestinoRequest destino,
        PagoRequest pago) {
}
