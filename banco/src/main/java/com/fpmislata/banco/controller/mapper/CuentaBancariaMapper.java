package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.response.CuentaBancariaDetailResponse;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

public class CuentaBancariaMapper {

    private static CuentaBancariaMapper INSTANCE;

    private CuentaBancariaMapper() {
    }

    public static CuentaBancariaMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CuentaBancariaMapper();
        }
        return INSTANCE;
    }

    public CuentaBancariaDetailResponse fromDtoToResponse(CuentaBancariaDto cuentaBancariaDto) {
        if (cuentaBancariaDto == null) {
            return null;
        }
        return new CuentaBancariaDetailResponse(
                cuentaBancariaDto.id(),
                cuentaBancariaDto.saldo(),
                cuentaBancariaDto.iban());
    }
}