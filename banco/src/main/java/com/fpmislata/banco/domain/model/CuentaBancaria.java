package com.fpmislata.banco.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaBancaria {
    private Long id;
    private BigDecimal saldo;
    private String iban;
}
