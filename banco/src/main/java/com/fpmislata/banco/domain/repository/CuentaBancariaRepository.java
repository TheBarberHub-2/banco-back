package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CuentaBancariaRepository {
    List<CuentaBancariaEntity> findByCliente(long clienteId);

    ClienteEntity getClienteByCuenta(long cuentaId);

    CuentaBancariaEntity getByTarjeta(long tarjetaId);

    Optional<CuentaBancariaEntity> getByIban(String iban);

    CuentaBancariaEntity updateSaldo(long cuentaId, BigDecimal saldo);
}
