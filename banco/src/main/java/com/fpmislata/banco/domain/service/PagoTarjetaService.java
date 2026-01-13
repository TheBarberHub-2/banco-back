package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.controller.webModel.request.AutorizacionRequest;
import com.fpmislata.banco.controller.webModel.request.DestinoRequest;
import com.fpmislata.banco.controller.webModel.request.OrigenRequest;
import com.fpmislata.banco.controller.webModel.request.PagoRequest;

public interface PagoTarjetaService {

    void pagoTarjeta(AutorizacionRequest autorizacionRequest, OrigenRequest origenRequest,
            DestinoRequest destinoRequest, PagoRequest pagoRequest);
}
