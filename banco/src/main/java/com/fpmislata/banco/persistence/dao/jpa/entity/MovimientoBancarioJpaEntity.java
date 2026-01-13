package com.fpmislata.banco.persistence.dao.jpa.entity;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_bancarios")
public class MovimientoBancarioJpaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaBancariaJpaEntity cuentaBancaria;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimientoBancario tipoMovimientoBancario;

    @Enumerated(EnumType.STRING)
    @Column(name = "origen_movimiento", nullable = false)
    private OrigenMovimientoBancario origenMovimientoBancario;

    @ManyToOne
    @JoinColumn(name = "tarjeta_credito_id")
    private TarjetaCreditoJpaEntity tarjetaCreditoOrigen;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "importe", nullable = false)
    private BigDecimal importe;

    @Column(name = "concepto", nullable = false)
    private String concepto;

    public MovimientoBancarioJpaEntity() {
    }

    public MovimientoBancarioJpaEntity(Long id, CuentaBancariaJpaEntity cuentaBancaria,
            TipoMovimientoBancario tipoMovimientoBancario,
            OrigenMovimientoBancario origenMovimientoBancario, TarjetaCreditoJpaEntity tarjetaCreditoOrigen,
            LocalDateTime fecha, BigDecimal importe, String concepto) {
        this.id = id;
        this.cuentaBancaria = cuentaBancaria;
        this.tipoMovimientoBancario = tipoMovimientoBancario;
        this.origenMovimientoBancario = origenMovimientoBancario;
        this.tarjetaCreditoOrigen = tarjetaCreditoOrigen;
        this.fecha = fecha;
        this.importe = importe;
        this.concepto = concepto;
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

    public TipoMovimientoBancario getTipoMovimientoBancario() {
        return tipoMovimientoBancario;
    }

    public void setTipoMovimientoBancario(TipoMovimientoBancario tipoMovimientoBancario) {
        this.tipoMovimientoBancario = tipoMovimientoBancario;
    }

    public OrigenMovimientoBancario getOrigenMovimientoBancario() {
        return origenMovimientoBancario;
    }

    public void setOrigenMovimientoBancario(OrigenMovimientoBancario origenMovimientoBancario) {
        this.origenMovimientoBancario = origenMovimientoBancario;
    }

    public TarjetaCreditoJpaEntity getTarjetaCreditoOrigen() {
        return tarjetaCreditoOrigen;
    }

    public void setTarjetaCreditoOrigen(TarjetaCreditoJpaEntity tarjetaCreditoOrigen) {
        this.tarjetaCreditoOrigen = tarjetaCreditoOrigen;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
