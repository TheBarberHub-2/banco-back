package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.domain.service.OperacionBancariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OperacionBancariaController {

    private final OperacionBancariaService operacionBancariaService;

    public OperacionBancariaController(OperacionBancariaService operacionBancariaService) {
        this.operacionBancariaService = operacionBancariaService;
    }

    @PostMapping("/transferencia")
    public ResponseEntity<MovimientoBancarioDetailResponse> transferencia(@RequestBody TransferenciaRequest request) {
        MovimientoBancarioDetailResponse response = operacionBancariaService.transferencia(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/pago_tarjeta")
    public ResponseEntity<MovimientoBancarioDetailResponse> pagoTarjeta(@RequestBody PagoTarjetaRequest request) {
        MovimientoBancarioDetailResponse response = operacionBancariaService.pagoTarjeta(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
