package com.fpmislata.banco.persistence.dao.jpa.impl;

import java.util.List;

import com.fpmislata.banco.persistence.dao.jpa.TarjetaCreditoJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class TarjetaCreditoJpaDaoImpl implements TarjetaCreditoJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TarjetaCreditoJpaEntity> findByCuentaBancaria(long cuentaBancariaId) {
        String sql = "SELECT t FROM TarjetaCreditoJpaEntity t WHERE t.cuentaBancaria.id = :cuentaBancariaId";
        return entityManager.createQuery(sql, TarjetaCreditoJpaEntity.class)
                .setParameter("cuentaBancariaId", cuentaBancariaId)
                .getResultList();
    }
}