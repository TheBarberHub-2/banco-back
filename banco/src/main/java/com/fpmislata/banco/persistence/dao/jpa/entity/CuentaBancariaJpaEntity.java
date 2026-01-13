package com.fpmislata.banco.persistence.dao.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas_bancarias")
public class CuentaBancariaJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteJpaEntity cliente;

    @Column(name = "iban", nullable = false, unique = true)
    private String iban;

    @Column(name = "saldo", nullable = false)
    private BigDecimal saldo;

    @OneToMany(mappedBy = "cuentaBancaria", cascade = CascadeType.ALL)
    private List<TarjetaCreditoJpaEntity> tarjetas;

    @OneToMany(mappedBy = "cuentaBancaria", cascade = CascadeType.ALL)
    private List<MovimientoBancarioJpaEntity> movimientos;

    public CuentaBancariaJpaEntity() {
    }

    public CuentaBancariaJpaEntity(Long id, ClienteJpaEntity cliente, String iban, BigDecimal saldo,
            List<TarjetaCreditoJpaEntity> tarjetas, List<MovimientoBancarioJpaEntity> movimientos) {
        this.id = id;
        this.cliente = cliente;
        this.iban = iban;
        this.saldo = saldo;
        this.tarjetas = tarjetas;
        this.movimientos = movimientos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteJpaEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteJpaEntity cliente) {
        this.cliente = cliente;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<TarjetaCreditoJpaEntity> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<TarjetaCreditoJpaEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<MovimientoBancarioJpaEntity> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoBancarioJpaEntity> movimientos) {
        this.movimientos = movimientos;
    }
}
