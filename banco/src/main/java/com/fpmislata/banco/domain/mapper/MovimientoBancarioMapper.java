package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper instance;

    private MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (instance == null) {
            instance = new MovimientoBancarioMapper();
        }
        return instance;
    }

    public MovimientoBancarioDto fromModelToDto(MovimientoBancario movimiento) {
        return new MovimientoBancarioDto(
                movimiento.getId(),
                movimiento.getTipoMovimientoBancario(),
                movimiento.getOrigenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromModelToDto(movimiento.getTarjetaCreditoOrigen()),
                movimiento.getFecha(),
                movimiento.getImporte(),
                movimiento.getConcepto());
    }

    public MovimientoBancario fromDtoToModel(MovimientoBancarioDto dto) {
        return new MovimientoBancario(
                dto.id(),
                dto.tipoMovimientoBancario(),
                dto.origenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromDtoToModel(dto.tarjetaCreditoOrigen()),
                dto.fecha(),
                dto.importe(),
                dto.concepto());
    }
}
