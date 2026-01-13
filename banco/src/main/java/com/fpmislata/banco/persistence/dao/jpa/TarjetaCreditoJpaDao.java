package com.fpmislata.banco.persistence.dao.jpa;

import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public interface TarjetaCreditoJpaDao {
    List<TarjetaCreditoJpaEntity> findByCuentaBancaria(long cuentaBancariaId);

    TarjetaCreditoJpaEntity findByNumeroTarjeta(String numeroTarjeta);
}
