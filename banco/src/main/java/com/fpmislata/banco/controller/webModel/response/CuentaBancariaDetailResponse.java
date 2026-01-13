package com.fpmislata.banco.controller.webModel.response;

import java.math.BigDecimal;

public record CuentaBancariaDetailResponse(
                BigDecimal saldo,
                String iban) {
}
