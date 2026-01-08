package com.fpmislata.banco.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjetas_credito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaCreditoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_tarjeta", nullable = false, unique = true, length = 30)
    private String numeroTarjeta;

    @Column(name = "fecha_caducidad", nullable = false, length = 5)
    private String fechaCaducidad;

    @Column(nullable = false, length = 3)
    private String cvc;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cuenta_id")
    private CuentaBancariaEntity cuenta;
}
