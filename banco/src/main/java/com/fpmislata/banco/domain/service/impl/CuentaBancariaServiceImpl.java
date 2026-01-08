package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.CuentaBancariaMapper;
import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.repository.CuentaBancariaRepository;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaBancariaServiceImpl implements CuentaBancariaService {

    private final CuentaBancariaRepository cuentaRepository;

    public CuentaBancariaServiceImpl(CuentaBancariaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Page<CuentaBancariaDto> findAll(int page, int size) {
        Page<CuentaBancaria> cuentaPage = cuentaRepository.findAll(page, size);
        List<CuentaBancariaDto> dtos = cuentaPage.data().stream()
                .map(CuentaBancariaMapper.getInstance()::fromModelToDto)
                .toList();
        return new Page<>(dtos, cuentaPage.pageNumber(),
                cuentaPage.pageSize(), cuentaPage.totalElements());
    }

    @Override
    public Optional<CuentaBancariaDto> findById(long id) {
        return cuentaRepository.findById(id)
                .map(CuentaBancariaMapper.getInstance()::fromModelToDto);
    }

    @Override
    public CuentaBancariaDto create(CuentaBancariaDto cuentaDto) {
        CuentaBancaria cuenta = CuentaBancariaMapper.getInstance().fromDtoToModel(cuentaDto);
        CuentaBancaria saved = cuentaRepository.save(cuenta);
        return CuentaBancariaMapper.getInstance().fromModelToDto(saved);
    }

    @Override
    public CuentaBancariaDto update(CuentaBancariaDto cuentaDto) {
        if (cuentaDto.id() == null || findById(cuentaDto.id()).isEmpty()) {
            throw new ResourceNotFoundException("Cuenta no encontrada con ID: " + cuentaDto.id());
        }
        CuentaBancaria cuenta = CuentaBancariaMapper.getInstance().fromDtoToModel(cuentaDto);
        CuentaBancaria updated = cuentaRepository.save(cuenta);
        return CuentaBancariaMapper.getInstance().fromModelToDto(updated);
    }

    @Override
    public void delete(long id) {
        if (findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Cuenta no encontrada con ID: " + id);
        }
        cuentaRepository.delete(id);
    }
}
