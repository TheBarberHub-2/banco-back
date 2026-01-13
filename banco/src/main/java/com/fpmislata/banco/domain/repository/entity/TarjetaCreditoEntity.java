package com.fpmislata.banco.domain.repository.entity;

public record TarjetaCreditoEntity(
        Long id,
        CuentaBancariaEntity cuenta,
        String numeroTarjeta,
        String fechaCaducidad,
        String cvc,
        String nombreCompleto) {
    public TarjetaCreditoEntity(
            Long id,
            CuentaBancariaEntity cuenta,
            String numeroTarjeta,
            String fechaCaducidad,
            String cvc,
            String nombreCompleto) {
        this.id = id;
        this.cuenta = cuenta;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.cvc = cvc;
        this.nombreCompleto = nombreCompleto;
    }
}
