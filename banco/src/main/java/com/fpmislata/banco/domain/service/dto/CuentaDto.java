package com.fpmislata.banco.domain.service.dto;

import com.fpmislata.banco.enums.TipoCuenta;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CuentaDto(
        Long id,

        @NotBlank(message = "El número de cuenta es obligatorio") @Size(min = 10, max = 20, message = "El número de cuenta debe tener entre 10 y 20 caracteres") String numeroCuenta,

        @NotNull(message = "El tipo de cuenta es obligatorio") TipoCuenta tipoCuenta,

        @NotNull(message = "El saldo es obligatorio") @Min(value = 0, message = "El saldo no puede ser negativo") Double saldo,

        @NotBlank(message = "El titular es obligatorio") @Size(min = 3, max = 200, message = "El titular debe tener entre 3 y 200 caracteres") String titular) {
}
