package com.fpmislata.banco.domain.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.fpmislata.banco.domain.mapper.ClienteMapper;
import com.fpmislata.banco.domain.mapper.CuentaBancariaMapper;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.exception.ResourceNotFoundException;

public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaBancariaRepository;

    public CuentaBancariaServiceImpl(CuentaBancariaRepository cuentaBancariaRepository) {
        this.cuentaBancariaRepository = cuentaBancariaRepository;
    }

    @Override
    public List<CuentaBancariaDto> findByCliente(long clienteId) {
        List<CuentaBancariaEntity> cuentasEntities = cuentaBancariaRepository.findByCliente(clienteId);

        List<CuentaBancariaDto> cuentasDto = cuentasEntities.stream()
                .map(CuentaBancariaMapper.getInstance()::fromEntityToModel)
                .map(CuentaBancariaMapper.getInstance()::fromModelToDto)
                .toList();

        return cuentasDto;
    }

    @Override
    public ClienteDto getClienteByCuenta(long cuentaId) {
        ClienteDto clienteDto = ClienteMapper.getInstance().fromModelToDto(
                ClienteMapper.getInstance().fromEntityToModel(cuentaBancariaRepository.getClienteByCuenta(cuentaId)));

        return clienteDto;
    }

    @Override
    public CuentaBancariaDto getByTarjeta(long tarjetaId) {
        CuentaBancariaDto cuentaBancariaDto = CuentaBancariaMapper.getInstance().fromModelToDto(
                CuentaBancariaMapper.getInstance().fromEntityToModel(cuentaBancariaRepository.getByTarjeta(tarjetaId)));
        return cuentaBancariaDto;
    }

    @Override
    public CuentaBancariaDto getByIban(String iban) {
        return cuentaBancariaRepository.getByIban(iban).map(CuentaBancariaMapper.getInstance()::fromEntityToModel)
                .map(CuentaBancariaMapper.getInstance()::fromModelToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta con iban: " + iban + " no encontrada"));
    }

    @Override
    public CuentaBancariaDto updateSaldo(long cuentaId, BigDecimal saldo) {
        CuentaBancariaDto cuentaBancariaDto = CuentaBancariaMapper.getInstance().fromModelToDto(CuentaBancariaMapper
                .getInstance().fromEntityToModel(cuentaBancariaRepository.updateSaldo(cuentaId, saldo)));

        return cuentaBancariaDto;
    }
}