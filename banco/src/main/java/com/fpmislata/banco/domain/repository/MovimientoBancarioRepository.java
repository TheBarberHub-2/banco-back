package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.model.Page;

import java.util.Optional;

public interface MovimientoBancarioRepository {
    Page<MovimientoBancario> findAll(int page, int size);

    Optional<MovimientoBancario> findById(long id);

    MovimientoBancario save(MovimientoBancario movimientoBancario);
}
