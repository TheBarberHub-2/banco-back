package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.Transferencia;
import com.fpmislata.banco.domain.service.dto.TransferenciaDto;

public class TransferenciaMapper {
    private static TransferenciaMapper instance;

    private TransferenciaMapper() {
    }

    public static TransferenciaMapper getInstance() {
        if (instance == null) {
            instance = new TransferenciaMapper();
        }
        return instance;
    }

    public TransferenciaDto fromModelToDto(Transferencia transferencia) {
        return new TransferenciaDto(
                transferencia.getId(),
                transferencia.getCuentaOrigen(),
                transferencia.getCuentaDestino(),
                transferencia.getMonto(),
                transferencia.getFecha(),
                transferencia.getConcepto());
    }

    public Transferencia fromDtoToModel(TransferenciaDto dto) {
        return new Transferencia(
                dto.id(),
                dto.cuentaOrigen(),
                dto.cuentaDestino(),
                dto.monto(),
                dto.fecha(),
                dto.concepto());
    }
}
