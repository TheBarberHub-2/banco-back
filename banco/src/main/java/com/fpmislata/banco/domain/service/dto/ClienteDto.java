package com.fpmislata.banco.domain.service.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
                Long id,
                @NotBlank(message = "El login es obligatorio") String login,
                @NotBlank(message = "La contraseña es obligatoria") String password,
                @NotBlank(message = "El nombre es obligatorio") String nombre,
                @NotBlank(message = "El primer apellido es obligatorio") String apellido1,
                String apellido2,
                @NotBlank(message = "El DNI es obligatorio") String dni,
                String apiToken,
                List<CuentaBancariaDto> cuentas) {

        public ClienteDto(Long id, String login, String password, String nombre, String apellido1,
                        String apellido2, String dni, String apiToken, List<CuentaBancariaDto> cuentas) {
                this.id = id;
                this.login = login;
                this.password = password;
                this.nombre = nombre;
                this.apellido1 = apellido1;
                this.apellido2 = apellido2;
                this.dni = dni;
                this.apiToken = apiToken;
                this.cuentas = cuentas == null ? List.of() : List.copyOf(cuentas);
        }
}
