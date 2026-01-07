package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.CuentaMapper;
import com.fpmislata.banco.domain.model.Cuenta;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.repository.CuentaRepository;
import com.fpmislata.banco.domain.service.CuentaService;
import com.fpmislata.banco.domain.service.dto.CuentaDto;
import com.fpmislata.banco.enums.TipoCuenta;
import com.fpmislata.banco.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    private final CuentaRepository cuentaRepository;

    public CuentaServiceImpl(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public Page<CuentaDto> findAll(int page, int size) {
        Page<Cuenta> cuentaPage = cuentaRepository.findAll(page, size);
        List<CuentaDto> dtos = cuentaPage.data().stream()
                .map(CuentaMapper.getInstance()::fromModelToDto)
                .toList();
        return new Page<>(dtos, cuentaPage.pageNumber(),
                cuentaPage.pageSize(), cuentaPage.totalElements());
    }

    @Override
    public Page<CuentaDto> findByTipoCuenta(TipoCuenta tipoCuenta, int page, int size) {
        Page<Cuenta> cuentaPage = cuentaRepository.findByTipoCuenta(tipoCuenta, page, size);
        List<CuentaDto> dtos = cuentaPage.data().stream()
                .map(CuentaMapper.getInstance()::fromModelToDto)
                .toList();
        return new Page<>(dtos, cuentaPage.pageNumber(),
                cuentaPage.pageSize(), cuentaPage.totalElements());
    }

    @Override
    public CuentaDto getById(long id) {
        return findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));
    }

    @Override
    public Optional<CuentaDto> findById(long id) {
        return cuentaRepository.findById(id)
                .map(CuentaMapper.getInstance()::fromModelToDto);
    }

    @Override
    public CuentaDto create(CuentaDto cuentaDto) {
        Cuenta cuenta = CuentaMapper.getInstance().fromDtoToModel(cuentaDto);
        Cuenta saved = cuentaRepository.save(cuenta);
        return CuentaMapper.getInstance().fromModelToDto(saved);
    }

    @Override
    public CuentaDto update(CuentaDto cuentaDto) {
        getById(cuentaDto.id()); // Verifica que existe
        Cuenta cuenta = CuentaMapper.getInstance().fromDtoToModel(cuentaDto);
        Cuenta updated = cuentaRepository.save(cuenta);
        return CuentaMapper.getInstance().fromModelToDto(updated);
    }

    @Override
    public void delete(long id) {
        getById(id); // Verifica que existe
        cuentaRepository.delete(id);
    }
}
