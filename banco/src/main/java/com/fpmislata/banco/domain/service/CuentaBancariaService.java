package com.fpmislata.banco.domain.service;

import java.math.BigDecimal;
import java.util.List;

import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

public interface CuentaBancariaService {
    List<CuentaBancariaDto> findByCliente(long clienteId);

    ClienteDto getClienteByCuenta(long cuentaId);

    CuentaBancariaDto getByTarjeta(long tarjetaId);

    CuentaBancariaDto getByIban(String iban);

    CuentaBancariaDto updateSaldo(long cuentaId, BigDecimal saldo);
}
