package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.TarjetaCredito;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

public class TarjetaCreditoMapper {
    private static TarjetaCreditoMapper instance;

    private TarjetaCreditoMapper() {
    }

    public static TarjetaCreditoMapper getInstance() {
        if (instance == null) {
            instance = new TarjetaCreditoMapper();
        }
        return instance;
    }

    public TarjetaCreditoDto fromModelToDto(TarjetaCredito tarjeta) {
        if (tarjeta == null)
            return null;
        return new TarjetaCreditoDto(
                tarjeta.getNumeroTarjeta(),
                tarjeta.getFechaCaducidad(),
                tarjeta.getCvc(),
                tarjeta.getNombreCompleto());
    }

    public TarjetaCredito fromDtoToModel(TarjetaCreditoDto dto) {
        if (dto == null)
            return null;
        return new TarjetaCredito(
                dto.numeroTarjeta(),
                dto.fechaCaducidad(),
                dto.cvc(),
                dto.nombreCompleto());
    }
}
