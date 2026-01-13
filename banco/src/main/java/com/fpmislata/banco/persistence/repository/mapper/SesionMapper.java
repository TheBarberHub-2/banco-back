package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.SesionEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.SesionJpaEntity;

public class SesionMapper {

    private static SesionMapper INSTANCE;

    private SesionMapper() {
    }

    public static SesionMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SesionMapper();
        }
        return INSTANCE;
    }

    public SesionJpaEntity fromEntityToJpa(SesionEntity sesionEntity) {
        if (sesionEntity == null) {
            return null;
        }
        return new SesionJpaEntity(
                sesionEntity.id(),
                ClienteMapper.getInstance().fromEntityToJpa(sesionEntity.usuario()),
                sesionEntity.token(),
                sesionEntity.expiredDate());
    }

    public SesionEntity fromJpaToEntity(SesionJpaEntity sesionJpaEntity) {
        if (sesionJpaEntity == null) {
            return null;
        }
        return new SesionEntity(
                sesionJpaEntity.getId(),
                ClienteMapper.getInstance().fromJpaToEntity(sesionJpaEntity.getUsuario()),
                sesionJpaEntity.getToken(),
                sesionJpaEntity.getExpiredDate());
    }
}
