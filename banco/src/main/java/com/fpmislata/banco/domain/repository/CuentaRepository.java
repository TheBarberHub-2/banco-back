package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.model.Cuenta;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.enums.TipoCuenta;

import java.util.Optional;

public interface CuentaRepository {
    Page<Cuenta> findAll(int page, int size);

    Page<Cuenta> findByTipoCuenta(TipoCuenta tipoCuenta, int page, int size);

    Optional<Cuenta> findById(long id);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    Cuenta save(Cuenta cuenta);

    void delete(long id);
}
