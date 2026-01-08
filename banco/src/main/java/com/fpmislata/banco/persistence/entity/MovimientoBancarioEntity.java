package com.fpmislata.banco.persistence.entity;

import com.fpmislata.banco.enums.OrigenMovimientoBancario;
import com.fpmislata.banco.enums.TipoMovimientoBancario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_bancarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoBancarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimientoBancario tipoMovimientoBancario;

    @Enumerated(EnumType.STRING)
    @Column(name = "origen_movimiento", nullable = false)
    private OrigenMovimientoBancario origenMovimientoBancario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tarjeta_credito_id")
    private TarjetaCreditoEntity tarjetaCreditoOrigen;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private BigDecimal importe;

    @Column(length = 255)
    private String concepto;
}
