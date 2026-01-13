package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.response.ClienteDetailResponse;
import com.fpmislata.banco.domain.service.dto.ClienteDto;

public class ClienteMapper {
    private static ClienteMapper INSTANCE;

    private ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClienteMapper();
        }
        return INSTANCE;
    }

    public ClienteDetailResponse fromDtoToResponse(ClienteDto clienteDto) {
        if (clienteDto == null) {
            return null;
        }
        return new ClienteDetailResponse(
                clienteDto.nombre(),
                clienteDto.apellido1(),
                clienteDto.apellido2(),
                clienteDto.dni());
    }
}
