package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

import java.util.Optional;

public interface MovimientoBancarioService {
    MovimientoBancarioDto create(MovimientoBancarioDto movimientoBancarioDto);

    Page<MovimientoBancarioDto> findAll(int page, int size);

    Optional<MovimientoBancarioDto> findById(long id);
}
