package com.fpmislata.banco.controller.webModel.request;

public record TransferenciaRequest(
        AutorizacionRequest autorizacion,
        OrigenTransferencia origen,
        DestinoRequest destino,
        PagoRequest pago) {

}
