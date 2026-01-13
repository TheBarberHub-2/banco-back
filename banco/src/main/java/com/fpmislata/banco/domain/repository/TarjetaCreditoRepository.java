package com.fpmislata.banco.domain.repository;

import java.util.List;

import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;

public interface TarjetaCreditoRepository {
    List<TarjetaCreditoEntity> findByCuentaBancaria(long cuentaBancariaId);

    TarjetaCreditoEntity findByNumeroTarjeta(String numeroTarjeta);
}
