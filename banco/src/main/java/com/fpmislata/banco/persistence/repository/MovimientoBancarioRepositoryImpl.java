package com.fpmislata.banco.persistence.repository;

import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.persistence.dao.jpa.MovimientoBancarioJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import com.fpmislata.banco.persistence.repository.mapper.MovimientoBancarioMapper;

import java.util.List;

public class MovimientoBancarioRepositoryImpl implements MovimientoBancarioRepository {

    private final MovimientoBancarioJpaDao movimientoBancarioJpaDao;

    public MovimientoBancarioRepositoryImpl(MovimientoBancarioJpaDao movimientoBancarioJpaDao) {
        this.movimientoBancarioJpaDao = movimientoBancarioJpaDao;
    }

    @Override
    public List<MovimientoBancarioEntity> findByCuenta(long cuentaId) {
        List<MovimientoBancarioJpaEntity> jpaEntities = movimientoBancarioJpaDao.findByCuentaBancaria(cuentaId);
        return jpaEntities.stream()
                .map(MovimientoBancarioMapper.getInstance()::fromJpaToEntity)
                .toList();
    }

    @Override
    public List<MovimientoBancarioEntity> findByTarjeta(long tarjetaId) {
        List<MovimientoBancarioJpaEntity> jpaEntities = movimientoBancarioJpaDao.findByTarjeta(tarjetaId);
        return jpaEntities.stream()
                .map(MovimientoBancarioMapper.getInstance()::fromJpaToEntity)
                .toList();
    }

    @Override
    public MovimientoBancarioEntity insert(MovimientoBancarioEntity movimientoBancarioEntity) {
        MovimientoBancarioJpaEntity jpaEntity = MovimientoBancarioMapper.getInstance()
                .fromEntityToJpa(movimientoBancarioEntity);
        MovimientoBancarioJpaEntity insertedJpaEntity = movimientoBancarioJpaDao.insert(jpaEntity);
        return MovimientoBancarioMapper.getInstance().fromJpaToEntity(insertedJpaEntity);
    }
}
