package com.fpmislata.banco.persistence.dao.jpa;

import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;

public interface CuentaBancariaJpaDao {
    List<CuentaBancariaJpaEntity> findByCliente(long clienteId);

    ClienteJpaEntity getClienteByCuenta(long cuentaId);
}
