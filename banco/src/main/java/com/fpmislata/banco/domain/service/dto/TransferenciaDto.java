package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TransferenciaDto(
        Long id,

        @NotBlank(message = "La cuenta de origen es obligatoria") String cuentaOrigen,

        @NotBlank(message = "La cuenta de destino es obligatoria") String cuentaDestino,

        @NotNull(message = "El monto es obligatorio") @Min(value = 1, message = "El monto debe ser mayor a 0") Double monto,

        LocalDateTime fecha,

        @Size(max = 200, message = "El concepto no puede exceder 200 caracteres") String concepto) {
}
