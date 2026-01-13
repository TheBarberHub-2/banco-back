package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.response.TarjetaCreditoDetailResponse;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

public class TarjetaCreditoMapper {
    private static TarjetaCreditoMapper INSTANCE;

    private TarjetaCreditoMapper() {
    }

    public static TarjetaCreditoMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TarjetaCreditoMapper();
        }
        return INSTANCE;
    }

    public TarjetaCreditoDetailResponse fromDtoToResponse(TarjetaCreditoDto tarjetaCreditoDto) {
        if (tarjetaCreditoDto == null) {
            return null;
        }
        return new TarjetaCreditoDetailResponse(
                tarjetaCreditoDto.numeroTarjeta(),
                tarjetaCreditoDto.fechaCaducidad(),
                tarjetaCreditoDto.cvc(),
                tarjetaCreditoDto.nombreCompleto());
    }
}
