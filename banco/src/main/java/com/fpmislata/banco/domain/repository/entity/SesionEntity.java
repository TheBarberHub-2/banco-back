package com.fpmislata.banco.domain.repository.entity;

import java.time.LocalDateTime;

public record SesionEntity(
        Long id,
        ClienteEntity usuario,
        String token,
        LocalDateTime expiredDate) {
    public SesionEntity(Long id, ClienteEntity usuario, String token, LocalDateTime expiredDate) {
        this.id = id;
        this.usuario = usuario;
        this.token = token;
        this.expiredDate = expiredDate;
    }
}
