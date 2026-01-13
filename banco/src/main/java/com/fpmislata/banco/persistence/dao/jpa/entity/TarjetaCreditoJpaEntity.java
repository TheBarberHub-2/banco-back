package com.fpmislata.banco.persistence.dao.jpa.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarjetas_credito")
public class TarjetaCreditoJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaBancariaJpaEntity cuentaBancaria;

    @Column(name = "numero_tarjeta", nullable = false, unique = true)
    private String numeroTarjeta;

    @Column(name = "fecha_caducidad", nullable = false)
    private String fechaCaducidad;

    @Column(name = "cvc", nullable = false)
    private String cvc;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    public TarjetaCreditoJpaEntity() {
    }

    public TarjetaCreditoJpaEntity(Long id, CuentaBancariaJpaEntity cuentaBancaria, String numeroTarjeta,
            String fechaCaducidad,
            String cvc, String nombreCompleto) {
        this.id = id;
        this.cuentaBancaria = cuentaBancaria;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaCaducidad = fechaCaducidad;
        this.cvc = cvc;
        this.nombreCompleto = nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CuentaBancariaJpaEntity getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaJpaEntity cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
