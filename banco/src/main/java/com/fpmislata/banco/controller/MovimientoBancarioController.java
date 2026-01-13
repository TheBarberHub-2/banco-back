package com.fpmislata.banco.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.mapper.MovimientoBancarioMapper;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.validation.RequireSameUser;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoBancarioController {

    private final MovimientoBancarioService movimientoBancarioService;

    public MovimientoBancarioController(MovimientoBancarioService movimientoBancarioService) {
        this.movimientoBancarioService = movimientoBancarioService;
    }

    @GetMapping("/cuenta/{cuentaId}")
    @RequireSameUser(paramName = "cuentaId", type = RequireSameUser.ParamType.CUENTA)
    public ResponseEntity<List<MovimientoBancarioDetailResponse>> findByCuenta(@PathVariable int cuentaId) {
        List<MovimientoBancarioDetailResponse> movimientos = movimientoBancarioService.findByCuenta(cuentaId)
                .stream()
                .map(MovimientoBancarioMapper.getInstance()::fromDtoToResponse)
                .toList();
        return ResponseEntity.ok(movimientos);
    }
}
