package com.fpmislata.banco.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.domain.service.TransferenciaService;

@RestController
@RequestMapping("/api/transferencia")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<Void> procesarTransferencia(
            @RequestBody TransferenciaRequest transferenciaRequest) {
        transferenciaService.transferencia(transferenciaRequest.autorizacion(), transferenciaRequest.origen(),
                transferenciaRequest.destino(), transferenciaRequest.pago());
        return ResponseEntity.ok().build();
    }
}
