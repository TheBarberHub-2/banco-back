package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record CuentaBancariaDto(
        Long id,
        @NotNull(message = "El saldo es obligatorio") BigDecimal saldo,
        @NotBlank(message = "El IBAN es obligatorio") @Size(min = 24, max = 24, message = "El IBAN debe tener 24 caracteres") String iban) {
}
