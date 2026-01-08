package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;

public interface OperacionBancariaService {
    MovimientoBancarioDetailResponse transferencia(TransferenciaRequest request);

    MovimientoBancarioDetailResponse pagoTarjeta(PagoTarjetaRequest request);
}
