package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.service.dto.ClienteDto;

public class ClienteMapper {
    private static ClienteMapper instance;

    private ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (instance == null) {
            instance = new ClienteMapper();
        }
        return instance;
    }

    public ClienteDto fromModelToDto(Cliente cliente) {
        return new ClienteDto(
                cliente.getLogin(),
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getDni(),
                cliente.getApiToken());
    }

    public Cliente fromDtoToModel(ClienteDto dto) {
        return new Cliente(
                dto.login(),
                null, // Password usually not in DTO or handled separately
                dto.nombre(),
                dto.apellido1(),
                dto.apellido2(),
                dto.dni(),
                dto.apiToken());
    }
}
