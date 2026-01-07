package com.fpmislata.banco.controller.webModel.request;

import com.fpmislata.banco.enums.TipoCuenta;

public record CuentaRequest(
        String numeroCuenta,
        TipoCuenta tipoCuenta,
        Double saldo,
        String titular) {
}
