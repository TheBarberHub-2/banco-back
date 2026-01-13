package com.fpmislata.banco.domain.service;

import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

import java.util.List;

public interface MovimientoBancarioService {
    List<MovimientoBancarioDto> findByCuenta(long cuentaId);
}
