package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper INSTANCE;

    private MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MovimientoBancarioMapper();
        }
        return INSTANCE;
    }

    public MovimientoBancarioDetailResponse fromDtoToResponse(MovimientoBancarioDto movimientoBancarioDto) {
        if (movimientoBancarioDto == null) {
            return null;
        }
        return new MovimientoBancarioDetailResponse(
                movimientoBancarioDto.id(),
                movimientoBancarioDto.tipoMovimientoBancario(),
                movimientoBancarioDto.origenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromDtoToResponse(movimientoBancarioDto.tarjetaCreditoOrigen()),
                movimientoBancarioDto.fecha(),
                movimientoBancarioDto.importe(),
                movimientoBancarioDto.concepto());
    }
}