package com.fpmislata.banco.persistence.dao.jpa.impl;

import java.util.List;
import java.util.Optional;

import com.fpmislata.banco.persistence.dao.jpa.ClienteJpaDao;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

public class ClienteJpaDaoImpl implements ClienteJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ClienteJpaEntity> findByLogin(String login) {
        String sql = "SELECT c FROM ClienteJpaEntity c WHERE c.login = :login";
        TypedQuery<ClienteJpaEntity> query = entityManager.createQuery(sql, ClienteJpaEntity.class);
        query.setParameter("login", login);

        List<ClienteJpaEntity> results = query.getResultList();
        if (results.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(results.get(0));
        }
    }
}