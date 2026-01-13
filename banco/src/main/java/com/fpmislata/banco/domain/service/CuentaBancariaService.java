package com.fpmislata.banco.domain.service;

import java.util.List;

import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

public interface CuentaBancariaService {
    List<CuentaBancariaDto> findByCliente(long clienteId);

    ClienteDto getClienteByCuenta(long cuentaId);
}
