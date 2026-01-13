package com.fpmislata.banco.controller.webModel.response;

import java.math.BigDecimal;

public record CuentaBancariaDetailResponse(
        Long id,
        BigDecimal saldo,
        String iban) {
}
