package com.fpmislata.banco.domain.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TarjetaCreditoDto(
                Long id,
                CuentaBancariaDto cuenta,
                @NotBlank(message = "El número de tarjeta es obligatorio") @Size(min = 16, max = 16) String numeroTarjeta,
                @NotBlank(message = "La fecha de caducidad es obligatoria") String fechaCaducidad,
                @NotBlank(message = "El CVC es obligatorio") @Size(min = 3, max = 3) String cvc,
                @NotBlank(message = "El nombre completo es obligatorio") String nombreCompleto) {

        public TarjetaCreditoDto(Long id, CuentaBancariaDto cuenta, String numeroTarjeta,
                        String fechaCaducidad, String cvc, String nombreCompleto) {
                this.id = id;
                this.cuenta = cuenta;
                this.numeroTarjeta = numeroTarjeta;
                this.fechaCaducidad = fechaCaducidad;
                this.cvc = cvc;
                this.nombreCompleto = nombreCompleto;
        }
}