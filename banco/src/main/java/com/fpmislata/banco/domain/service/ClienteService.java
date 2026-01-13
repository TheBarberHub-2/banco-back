package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.ClienteDto;

public interface ClienteService {
    ClienteDto getByLogin(String login);
}
