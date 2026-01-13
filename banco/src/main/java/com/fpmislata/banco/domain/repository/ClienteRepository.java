package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;

import java.util.Optional;

public interface ClienteRepository {
    Optional<ClienteEntity> findByLogin(String login);
}
