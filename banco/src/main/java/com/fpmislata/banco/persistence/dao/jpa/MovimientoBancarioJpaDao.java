package com.fpmislata.banco.persistence.dao.jpa;

import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

public interface MovimientoBancarioJpaDao {
    List<MovimientoBancarioJpaEntity> findByCuentaBancaria(long cuentaBancariaId);

    List<MovimientoBancarioJpaEntity> findByTarjeta(long tarjetaId);

    MovimientoBancarioJpaEntity insert(MovimientoBancarioJpaEntity movimientoBancarioJpaEntity);
}
