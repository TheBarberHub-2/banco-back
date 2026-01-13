package com.fpmislata.banco.domain.service.impl;

import java.util.List;

import com.fpmislata.banco.domain.mapper.ClienteMapper;
import com.fpmislata.banco.domain.mapper.CuentaBancariaMapper;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

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

}