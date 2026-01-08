package com.fpmislata.banco.controller.mapper;

import com.fpmislata.banco.controller.webModel.request.MovimientoBancarioRequest;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.controller.webModel.response.TarjetaCreditoDetailResponse;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

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

    public MovimientoBancarioDto fromRequestToDto(MovimientoBancarioRequest request) {
        TarjetaCreditoDto tarjetaDto = null;
        if (request.tarjetaCreditoOrigen() != null) {
            tarjetaDto = new TarjetaCreditoDto(
                    request.tarjetaCreditoOrigen().numeroTarjeta(),
                    request.tarjetaCreditoOrigen().fechaCaducidad(),
                    request.tarjetaCreditoOrigen().cvc(),
                    request.tarjetaCreditoOrigen().nombreCompleto());
        }

        return new MovimientoBancarioDto(
                null,
                request.tipoMovimientoBancario(),
                request.origenMovimientoBancario(),
                tarjetaDto,
                request.fecha(),
                request.importe(),
                request.concepto());
    }

    public MovimientoBancarioDetailResponse fromDtoToDetail(MovimientoBancarioDto dto) {
        TarjetaCreditoDetailResponse tarjetaResponse = null;
        if (dto.tarjetaCreditoOrigen() != null) {
            tarjetaResponse = new TarjetaCreditoDetailResponse(
                    dto.tarjetaCreditoOrigen().numeroTarjeta(),
                    dto.tarjetaCreditoOrigen().fechaCaducidad(),
                    dto.tarjetaCreditoOrigen().cvc(),
                    dto.tarjetaCreditoOrigen().nombreCompleto());
        }

        return new MovimientoBancarioDetailResponse(
                dto.id(),
                dto.tipoMovimientoBancario(),
                dto.origenMovimientoBancario(),
                tarjetaResponse,
                dto.fecha(),
                dto.importe(),
                dto.concepto());
    }
}
