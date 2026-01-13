package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;

import java.util.List;

public interface MovimientoBancarioRepository {
    List<MovimientoBancarioEntity> findByCuenta(long cuentaId);
}
