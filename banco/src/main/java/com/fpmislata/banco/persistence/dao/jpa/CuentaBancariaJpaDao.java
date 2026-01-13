package com.fpmislata.banco.persistence.dao.jpa;

import java.math.BigDecimal;
import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;

public interface CuentaBancariaJpaDao {
    List<CuentaBancariaJpaEntity> findByCliente(long clienteId);

    ClienteJpaEntity getClienteByCuenta(long cuentaId);

    CuentaBancariaJpaEntity getByIban(String iban);

    CuentaBancariaJpaEntity updateSaldo(long cuentaId, BigDecimal saldo);
}
