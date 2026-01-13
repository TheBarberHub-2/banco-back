package com.fpmislata.banco.domain.repository.entity;

import java.math.BigDecimal;
import java.util.List;

public record CuentaBancariaEntity(
        Long id,
        ClienteEntity cliente,
        String iban,
        BigDecimal saldo,
        List<TarjetaCreditoEntity> tarjetas,
        List<MovimientoBancarioEntity> movimientos) {
    public CuentaBancariaEntity(
            Long id,
            ClienteEntity cliente,
            String iban,
            BigDecimal saldo,
            List<TarjetaCreditoEntity> tarjetas,
            List<MovimientoBancarioEntity> movimientos) {
        this.id = id;
        this.cliente = cliente;
        this.iban = iban;
        this.saldo = saldo;
        this.tarjetas = tarjetas;
        this.movimientos = movimientos;
    }
}
