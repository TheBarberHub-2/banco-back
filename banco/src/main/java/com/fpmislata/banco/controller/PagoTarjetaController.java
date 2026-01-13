package com.fpmislata.banco.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco.domain.service.PagoTarjetaService;

@RestController
@RequestMapping("/api/pagoTarjeta")
public class PagoTarjetaController {

    private final PagoTarjetaService pagoTarjetaService;

    public PagoTarjetaController(PagoTarjetaService pagoTarjetaService) {
        this.pagoTarjetaService = pagoTarjetaService;
    }

    @PostMapping
    public ResponseEntity<Void> procesarPagoTarjeta(
            @RequestBody PagoTarjetaRequest pagoTarjetaRequest) {
        pagoTarjetaService.pagoTarjeta(pagoTarjetaRequest.autorizacion(), pagoTarjetaRequest.origen(),
                pagoTarjetaRequest.destino(), pagoTarjetaRequest.pago());
        return ResponseEntity.ok().build();
    }
}
