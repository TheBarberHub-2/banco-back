package com.fpmislata.banco.persistence.dao.jpa.impl;

import java.util.List;
import java.util.Optional;

import com.fpmislata.banco.persistence.dao.jpa.TarjetaCreditoJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

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

    @Override
    public Optional<TarjetaCreditoJpaEntity> findByNumeroTarjeta(String numeroTarjeta) {
        String sql = "SELECT t FROM TarjetaCreditoJpaEntity t WHERE t.numeroTarjeta = :numeroTarjeta";
        TypedQuery<TarjetaCreditoJpaEntity> query = entityManager.createQuery(sql, TarjetaCreditoJpaEntity.class);
        query.setParameter("numeroTarjeta", numeroTarjeta);

        List<TarjetaCreditoJpaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }
}