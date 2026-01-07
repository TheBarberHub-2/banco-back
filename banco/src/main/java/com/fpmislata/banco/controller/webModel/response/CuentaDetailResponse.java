package com.fpmislata.banco.controller.webModel.response;

import com.fpmislata.banco.enums.TipoCuenta;

public record CuentaDetailResponse(
        Long id,
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        Double saldo,
        String titular) {
}
