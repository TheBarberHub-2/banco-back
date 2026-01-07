package com.fpmislata.banco.domain.repository;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.model.Transferencia;

import java.util.Optional;

public interface TransferenciaRepository {
    Page<Transferencia> findAll(int page, int size);

    Optional<Transferencia> findById(long id);

    Transferencia save(Transferencia transferencia);
}
