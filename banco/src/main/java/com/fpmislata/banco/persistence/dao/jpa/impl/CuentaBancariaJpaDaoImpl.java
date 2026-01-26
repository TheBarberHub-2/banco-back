package com.fpmislata.banco.persistence.dao.jpa.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.fpmislata.banco.exception.ResourceNotFoundException;
import com.fpmislata.banco.persistence.dao.jpa.CuentaBancariaJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

public class CuentaBancariaJpaDaoImpl implements CuentaBancariaJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CuentaBancariaJpaEntity> findByCliente(long clienteId) {
        String sql = "SELECT c FROM CuentaBancariaJpaEntity c WHERE c.cliente.id = :clienteId";
        return entityManager.createQuery(sql, CuentaBancariaJpaEntity.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    @Override
    public ClienteJpaEntity getClienteByCuenta(long cuentaId) {
        String sql = "SELECT cliente FROM CuentaBancariaJpaEntity c WHERE c.id = :cuentaId";
        return entityManager.createQuery(sql, ClienteJpaEntity.class)
                .setParameter("cuentaId", cuentaId)
                .getSingleResult();
    }

    @Override
    public CuentaBancariaJpaEntity getByTarjeta(long tarjetaId) {
        String sql = "SELECT cuentaBancaria FROM TarjetaCreditoJpaEntity t WHERE t.id = :tarjetaId";
        return entityManager.createQuery(sql, CuentaBancariaJpaEntity.class)
                .setParameter("tarjetaId", tarjetaId)
                .getSingleResult();
    }

    @Override
    public Optional<CuentaBancariaJpaEntity> getByIban(String iban) {
        String sql = "SELECT c FROM CuentaBancariaJpaEntity c WHERE c.iban = :iban";
        TypedQuery<CuentaBancariaJpaEntity> query = entityManager.createQuery(sql, CuentaBancariaJpaEntity.class);
        query.setParameter("iban", iban);

        List<CuentaBancariaJpaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }

    @Transactional
    @Override
    public CuentaBancariaJpaEntity updateSaldo(long cuentaId, BigDecimal saldo) {
        CuentaBancariaJpaEntity managed = entityManager.find(CuentaBancariaJpaEntity.class,
                cuentaId);
        if (managed == null) {
            throw new ResourceNotFoundException("Cuenta con ID: " + cuentaId + " not found");
        }
        managed.setSaldo(managed.getSaldo().add(saldo));
        entityManager.flush();
        return entityManager.merge(managed);
    }
}