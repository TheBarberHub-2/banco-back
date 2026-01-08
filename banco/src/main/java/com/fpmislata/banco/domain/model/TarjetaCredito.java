package com.fpmislata.banco.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaCredito {
    private String numeroTarjeta;
    private String fechaCaducidad;
    private String cvc;
    private String nombreCompleto;
}
