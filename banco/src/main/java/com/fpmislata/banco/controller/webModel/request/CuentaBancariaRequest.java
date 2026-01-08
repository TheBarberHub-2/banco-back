package com.fpmislata.banco.controller.webModel.request;

import java.math.BigDecimal;

public record CuentaBancariaRequest(
        BigDecimal saldo,
        String iban) {
}
