package com.fpmislata.banco.controller.webModel.request;

public record PagoTarjetaRequest(
                AutorizacionRequest autorizacion,
                OrigenPagoTarjetaRequest origen,
                DestinoRequest destino,
                PagoRequest pago) {
}
