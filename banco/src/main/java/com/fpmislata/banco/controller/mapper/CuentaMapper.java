package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.request.CuentaRequest;
import com.fpmislata.banco.controller.webModel.response.CuentaDetailResponse;
import com.fpmislata.banco.domain.service.dto.CuentaDto;

public class CuentaMapper {
    private static CuentaMapper instance;

    private CuentaMapper() {
    }

    public static CuentaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaMapper();
        }
        return instance;
    }

    public CuentaDto fromRequestToDto(CuentaRequest request) {
        return new CuentaDto(
                null,
                request.numeroCuenta(),
                request.tipoCuenta(),
                request.saldo(),
                request.titular());
    }

    public CuentaDetailResponse fromDtoToDetail(CuentaDto dto) {
        return new CuentaDetailResponse(
                dto.id(),
                dto.numeroCuenta(),
                dto.tipoCuenta(),
                dto.saldo(),
                dto.titular());
    }
}
