package com.fpmislata.banco.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class CuentaBancaria {
    private Long id;
    private Cliente cliente;
    private BigDecimal saldo;
    private String iban;
    private List<TarjetaCredito> tarjetas;
    private List<MovimientoBancario> movimientos;

    public CuentaBancaria(Long id, Cliente cliente, BigDecimal saldo, String iban, List<TarjetaCredito> tarjetas,
            List<MovimientoBancario> movimientos) {
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldo;
        this.iban = iban;
        this.tarjetas = tarjetas;
        this.movimientos = movimientos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<TarjetaCredito> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaCredito> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<MovimientoBancario> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoBancario> movimientos) {
        this.movimientos = movimientos;
    }
}
