package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.request.CuentaBancariaRequest;
import com.fpmislata.banco.controller.webModel.response.CuentaBancariaDetailResponse;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

public class CuentaBancariaMapper {
    private static CuentaBancariaMapper instance;

    private CuentaBancariaMapper() {
    }

    public static CuentaBancariaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaBancariaMapper();
        }
        return instance;
    }

    public CuentaBancariaDto fromRequestToDto(CuentaBancariaRequest request) {
        return new CuentaBancariaDto(
                null,
                request.saldo(),
                request.iban());
    }

    public CuentaBancariaDetailResponse fromDtoToDetail(CuentaBancariaDto dto) {
        return new CuentaBancariaDetailResponse(
                dto.id(),
                dto.saldo(),
                dto.iban());
    }
}
