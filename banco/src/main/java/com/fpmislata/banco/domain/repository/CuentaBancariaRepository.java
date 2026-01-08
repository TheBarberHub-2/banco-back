package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.Page;

import java.util.Optional;

public interface CuentaBancariaRepository {
    Page<CuentaBancaria> findAll(int page, int size);

    Optional<CuentaBancaria> findById(long id);

    Optional<CuentaBancaria> findByIban(String iban);

    CuentaBancaria save(CuentaBancaria cuentaBancaria);

    void delete(long id);
}
