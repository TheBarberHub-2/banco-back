package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.dto.TransferenciaDto;

import java.util.Optional;

public interface TransferenciaService {
    TransferenciaDto ejecutarTransferencia(TransferenciaDto transferenciaDto);

    Page<TransferenciaDto> findAll(int page, int size);

    Optional<TransferenciaDto> findById(long id);
}
