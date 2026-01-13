package com.fpmislata.banco.domain.service;

import java.util.List;

import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

public interface TarjetaCreditoService {
    List<TarjetaCreditoDto> findByCuentaBancaria(long cuentaBancariaId);

    TarjetaCreditoDto findByNumeroTarjeta(String numeroTarjeta);
}
