package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

import java.util.Optional;

public interface CuentaBancariaService {
    Page<CuentaBancariaDto> findAll(int page, int size);

    Optional<CuentaBancariaDto> findById(long id);

    CuentaBancariaDto create(CuentaBancariaDto cuentaBancariaDto);

    CuentaBancariaDto update(CuentaBancariaDto cuentaBancariaDto);

    void delete(long id);
}
