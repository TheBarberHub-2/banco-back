package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.CuentaBancaria;
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

    public CuentaBancariaDto fromModelToDto(CuentaBancaria cuenta) {
        return new CuentaBancariaDto(
                cuenta.getId(),
                cuenta.getSaldo(),
                cuenta.getIban());
    }

    public CuentaBancaria fromDtoToModel(CuentaBancariaDto dto) {
        return new CuentaBancaria(
                dto.id(),
                dto.saldo(),
                dto.iban());
    }
}
