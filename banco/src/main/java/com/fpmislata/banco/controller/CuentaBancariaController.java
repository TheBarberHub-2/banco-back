package com.fpmislata.banco.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.mapper.CuentaBancariaMapper;
import com.fpmislata.banco.controller.webModel.response.CuentaBancariaDetailResponse;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.validation.RequireSameUser;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaBancariaService;

    public CuentaBancariaController(CuentaBancariaService cuentaBancariaService) {
        this.cuentaBancariaService = cuentaBancariaService;
    }

    @GetMapping("/cliente/{clienteId}")
    @RequireSameUser(paramName = "clienteId", type = RequireSameUser.ParamType.CLIENTE)
    public ResponseEntity<List<CuentaBancariaDetailResponse>> findByCliente(@PathVariable int clienteId) {
        List<CuentaBancariaDetailResponse> cuentas = cuentaBancariaService.findByCliente(clienteId)
                .stream()
                .map(CuentaBancariaMapper.getInstance()::fromDtoToResponse)
                .toList();
        return ResponseEntity.ok(cuentas);
    }
}
