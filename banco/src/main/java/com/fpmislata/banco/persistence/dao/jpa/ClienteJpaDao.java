package com.fpmislata.banco.persistence.dao.jpa;

import java.util.Optional;

import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;

public interface ClienteJpaDao {
    Optional<ClienteJpaEntity> findByLogin(String login);
}
