package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.ClienteMapper;
import com.fpmislata.banco.domain.repository.ClienteRepository;
import com.fpmislata.banco.domain.service.ClienteService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.exception.ResourceNotFoundException;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteDto getByLogin(String login) {
        return clienteRepository.findByLogin(login).map(ClienteMapper.getInstance()::fromEntityToModel)
                .map(ClienteMapper.getInstance()::fromModelToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario inválido"));
    }

}
