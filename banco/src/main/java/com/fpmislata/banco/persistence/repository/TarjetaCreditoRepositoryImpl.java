package com.fpmislata.banco.persistence.repository;

import java.util.List;

import com.fpmislata.banco.domain.repository.TarjetaCreditoRepository;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.persistence.dao.jpa.TarjetaCreditoJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.TarjetaCreditoMapper;

public class TarjetaCreditoRepositoryImpl implements TarjetaCreditoRepository {

    private final TarjetaCreditoJpaDao tarjetaCreditoJpaDao;

    public TarjetaCreditoRepositoryImpl(TarjetaCreditoJpaDao tarjetaCreditoJpaDao) {
        this.tarjetaCreditoJpaDao = tarjetaCreditoJpaDao;
    }

    @Override
    public List<TarjetaCreditoEntity> findByCuentaBancaria(long cuentaBancariaId) {
        List<TarjetaCreditoJpaEntity> jpaEntities = tarjetaCreditoJpaDao.findByCuentaBancaria(cuentaBancariaId);
        return jpaEntities.stream()
                .map(TarjetaCreditoMapper.getInstance()::fromJpaToEntity)
                .toList();
    }

}
