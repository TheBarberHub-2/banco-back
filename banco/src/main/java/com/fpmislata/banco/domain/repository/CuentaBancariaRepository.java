package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;

import java.util.List;

public interface CuentaBancariaRepository {
    List<CuentaBancariaEntity> findByCliente(long clienteId);

    ClienteEntity getClienteByCuenta(long cuentaId);
}
