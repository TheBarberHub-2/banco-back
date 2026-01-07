package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.Cuenta;
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

    public CuentaDto fromModelToDto(Cuenta cuenta) {
        return new CuentaDto(
                cuenta.getId(),
                cuenta.getNumeroCuenta(),
                cuenta.getTipoCuenta(),
                cuenta.getSaldo(),
                cuenta.getTitular());
    }

    public Cuenta fromDtoToModel(CuentaDto dto) {
        return new Cuenta(
                dto.id(),
                dto.numeroCuenta(),
                dto.tipoCuenta(),
                dto.saldo(),
                dto.titular());
    }
}
