package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.SesionDto;

public interface SesionService {
    SesionDto getByToken(String token);

    SesionDto create(SesionDto sesionDto);

    void deleteByToken(String token);
}
