package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.dto.CuentaDto;
import com.fpmislata.banco.enums.TipoCuenta;

import java.util.Optional;

public interface CuentaService {
    Page<CuentaDto> findAll(int page, int size);

    Page<CuentaDto> findByTipoCuenta(TipoCuenta tipoCuenta, int page, int size);

    CuentaDto getById(long id);

    Optional<CuentaDto> findById(long id);

    CuentaDto create(CuentaDto cuentaDto);

    CuentaDto update(CuentaDto cuentaDto);

    void delete(long id);
}
