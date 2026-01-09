package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
        @NotBlank Long  id,
        @NotBlank String login,
        @NotBlank String nombre,
        @NotBlank String apellido1,
        String apellido2,
        @NotBlank String dni,
        String apiToken) {
}
