package com.fpmislata.banco.domain.repository;

import java.util.List;
import java.util.Optional;

import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;

public interface TarjetaCreditoRepository {
    List<TarjetaCreditoEntity> findByCuentaBancaria(long cuentaBancariaId);

    Optional<TarjetaCreditoEntity> findByNumeroTarjeta(String numeroTarjeta);
}
