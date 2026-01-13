package com.fpmislata.banco.persistence.dao.jpa;

import java.util.Optional;

import com.fpmislata.banco.persistence.dao.jpa.entity.SesionJpaEntity;

public interface SesionJpaDao {
    Optional<SesionJpaEntity> findByToken(String token);

    SesionJpaEntity insert(SesionJpaEntity entity);

    void deleteByToken(String token);
}
