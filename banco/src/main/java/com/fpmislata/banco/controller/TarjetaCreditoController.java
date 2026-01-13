package com.fpmislata.banco.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.mapper.TarjetaCreditoMapper;
import com.fpmislata.banco.controller.webModel.response.TarjetaCreditoDetailResponse;
import com.fpmislata.banco.domain.service.TarjetaCreditoService;
import com.fpmislata.banco.domain.validation.RequireSameUser;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaCreditoController {

    private final TarjetaCreditoService tarjetaCreditoService;

    public TarjetaCreditoController(TarjetaCreditoService tarjetaCreditoService) {
        this.tarjetaCreditoService = tarjetaCreditoService;
    }

    @GetMapping("/cuenta/{cuentaId}")
    @RequireSameUser(paramName = "cuentaId", type = RequireSameUser.ParamType.CUENTA)
    public ResponseEntity<List<TarjetaCreditoDetailResponse>> findByCuenta(@PathVariable int cuentaId) {
        List<TarjetaCreditoDetailResponse> tarjetas = tarjetaCreditoService.findByCuentaBancaria(cuentaId)
                .stream()
                .map(TarjetaCreditoMapper.getInstance()::fromDtoToResponse)
                .toList();
        return ResponseEntity.ok(tarjetas);
    }
}
