package com.fpmislata.banco.domain.service.dto;

import java.time.LocalDateTime;

public record SesionDto(
        Long id,

        ClienteDto usuario,

        String token,

        LocalDateTime expiredDate) {
    public SesionDto(
            Long id,
            ClienteDto usuario,
            String token,
            LocalDateTime expiredDate) {
        this.id = id;
        this.usuario = usuario;
        this.token = token;
        this.expiredDate = expiredDate;
    }
}
