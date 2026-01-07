package com.fpmislata.banco.domain.model;

import com.fpmislata.banco.enums.TipoCuenta;

public class Cuenta {
    private Long id;
    private String numeroCuenta;
    private TipoCuenta tipoCuenta;
    private Double saldo;
    private String titular;

    public Cuenta() {
    }

    public Cuenta(Long id, String numeroCuenta, TipoCuenta tipoCuenta, Double saldo, String titular) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldo = saldo;
        this.titular = titular;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
}
