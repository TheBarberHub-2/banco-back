package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.ClienteDto;

public interface AuthService {
    String logIn(String login, String password);

    void logOut(String token);

    ClienteDto getByToken(String token);
}
