package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.persistence.dao.jpa.CuentaBancariaJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.ClienteMapper;
import com.fpmislata.banco.persistence.repository.mapper.CuentaBancariaMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class CuentaBancariaRepositoryImpl implements CuentaBancariaRepository {

    private final CuentaBancariaJpaDao cuentaBancariaJpaDao;

    public CuentaBancariaRepositoryImpl(CuentaBancariaJpaDao cuentaBancariaJpaDao) {
        this.cuentaBancariaJpaDao = cuentaBancariaJpaDao;
    }

    @Override
    public List<CuentaBancariaEntity> findByCliente(long clienteId) {
        List<CuentaBancariaJpaEntity> jpaEntities = cuentaBancariaJpaDao.findByCliente(clienteId);
        return jpaEntities.stream()
                .map(CuentaBancariaMapper.getInstance()::fromJpaToEntity)
                .toList();
    }

    @Override
    public ClienteEntity getClienteByCuenta(long cuentaId) {
        ClienteEntity clienteEntity = ClienteMapper.getInstance()
                .fromJpaToEntity(cuentaBancariaJpaDao.getClienteByCuenta(cuentaId));
        return clienteEntity;
    }

    @Override
    public Optional<CuentaBancariaEntity> getByIban(String iban) {
        return cuentaBancariaJpaDao.getByIban(iban).map(CuentaBancariaMapper.getInstance()::fromJpaToEntity);
    }

    @Override
    public CuentaBancariaEntity updateSaldo(long cuentaId, BigDecimal saldo) {
        CuentaBancariaEntity cuentaBancariaEntity = CuentaBancariaMapper.getInstance()
                .fromJpaToEntity(cuentaBancariaJpaDao.updateSaldo(cuentaId, saldo));

        return cuentaBancariaEntity;
    }
}
