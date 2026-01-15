package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.controller.webModel.request.AutorizacionRequest;
import com.fpmislata.banco.controller.webModel.request.DestinoRequest;
import com.fpmislata.banco.controller.webModel.request.OrigenTransferencia;
import com.fpmislata.banco.controller.webModel.request.PagoRequest;

public interface TransferenciaService {

    void transferencia(AutorizacionRequest autorizacionRequest, OrigenTransferencia origenRequest,
            DestinoRequest destinoRequest, PagoRequest pagoRequest);
}
