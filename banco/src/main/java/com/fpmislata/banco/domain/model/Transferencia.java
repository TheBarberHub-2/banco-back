package com.fpmislata.banco.domain.model;

import java.time.LocalDateTime;

public class Transferencia {
    private Long id;
    private String cuentaOrigen;
    private String cuentaDestino;
    private Double monto;
    private LocalDateTime fecha;
    private String concepto;

    public Transferencia() {
    }

    public Transferencia(Long id, String cuentaOrigen, String cuentaDestino, Double monto,
            LocalDateTime fecha, String concepto) {
        this.id = id;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.fecha = fecha;
        this.concepto = concepto;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
