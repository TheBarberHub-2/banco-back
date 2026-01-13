package com.fpmislata.banco.domain.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fpmislata.banco.domain.service.AuthService;
import com.fpmislata.banco.domain.service.ClienteService;
import com.fpmislata.banco.domain.service.SesionService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.SesionDto;
import com.fpmislata.banco.exception.BusinessException;

public class AuthServiceImpl implements AuthService {
    private final ClienteService clienteService;
    private final SesionService sesionService;

    public AuthServiceImpl(ClienteService clienteService, SesionService sesionService) {
        this.clienteService = clienteService;
        this.sesionService = sesionService;
    }

    @Override
    public String logIn(String login, String password) {

        ClienteDto usuario = clienteService.getByLogin(login);
        if (!usuario.password().equals(password)) {
            throw new BusinessException("Contraseña Incorrecta");
        }
        String token = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();

        SesionDto sesion = new SesionDto(null, usuario, token, date);

        sesionService.create(sesion);

        return token;

    }

    @Override
    public void logOut(String token) {
        sesionService.deleteByToken(token);
    }

    @Override
    public ClienteDto getByToken(String token) {
        SesionDto sesion = sesionService.getByToken(token);
        ClienteDto usuario = sesion.usuario();
        return usuario;
    }
}
