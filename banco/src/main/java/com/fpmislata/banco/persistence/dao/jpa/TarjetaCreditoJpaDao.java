package com.fpmislata.banco.persistence.dao.jpa;

import java.util.List;
import java.util.Optional;

import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public interface TarjetaCreditoJpaDao {
    List<TarjetaCreditoJpaEntity> findByCuentaBancaria(long cuentaBancariaId);

    Optional<TarjetaCreditoJpaEntity> findByNumeroTarjeta(String numeroTarjeta);
}
