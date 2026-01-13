package com.fpmislata.banco.persistence.dao.jpa.impl;

import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.MovimientoBancarioJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class MovimientoBancarioJpaDaoImpl implements MovimientoBancarioJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovimientoBancarioJpaEntity> findByCuentaBancaria(long cuentaBancariaId) {
        String sql = "SELECT m FROM MovimientoBancarioJpaEntity m WHERE m.cuentaBancaria.id = :cuentaBancariaId";
        return entityManager.createQuery(sql, MovimientoBancarioJpaEntity.class)
                .setParameter("cuentaBancariaId", cuentaBancariaId)
                .getResultList();
    }
}