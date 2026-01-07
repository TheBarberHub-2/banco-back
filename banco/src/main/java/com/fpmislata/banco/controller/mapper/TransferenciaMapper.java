package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.controller.webModel.response.TransferenciaDetailResponse;
import com.fpmislata.banco.domain.service.dto.TransferenciaDto;

public class TransferenciaMapper {
    private static TransferenciaMapper instance;

    private TransferenciaMapper() {
    }

    public static TransferenciaMapper getInstance() {
        if (instance == null) {
            instance = new TransferenciaMapper();
        }
        return instance;
    }

    public TransferenciaDto fromRequestToDto(TransferenciaRequest request) {
        return new TransferenciaDto(
                null,
                request.cuentaOrigen(),
                request.cuentaDestino(),
                request.monto(),
                null,
                request.concepto());
    }

    public TransferenciaDetailResponse fromDtoToDetail(TransferenciaDto dto) {
        return new TransferenciaDetailResponse(
                dto.id(),
                dto.cuentaOrigen(),
                dto.cuentaDestino(),
                dto.monto(),
                dto.fecha(),
                dto.concepto());
    }
}
