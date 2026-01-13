package com.fpmislata.banco.domain.service.impl;

import java.util.List;

import com.fpmislata.banco.domain.mapper.TarjetaCreditoMapper;
import com.fpmislata.banco.domain.repository.TarjetaCreditoRepository;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.domain.service.TarjetaCreditoService;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;
import com.fpmislata.banco.exception.ResourceNotFoundException;

public class TarjetaCreditoServiceImpl implements TarjetaCreditoService {
    private final TarjetaCreditoRepository tarjetaCreditoRepository;

    public TarjetaCreditoServiceImpl(TarjetaCreditoRepository tarjetaCreditoRepository) {
        this.tarjetaCreditoRepository = tarjetaCreditoRepository;
    }

    @Override
    public List<TarjetaCreditoDto> findByCuentaBancaria(long cuentaBancariaId) {
        List<TarjetaCreditoEntity> entities = tarjetaCreditoRepository.findByCuentaBancaria(cuentaBancariaId);

        List<TarjetaCreditoDto> dtos = entities.stream()
                .map(TarjetaCreditoMapper.getInstance()::fromEntityToModel)
                .map(TarjetaCreditoMapper.getInstance()::fromModelToDto)
                .toList();
        return dtos;
    }

    @Override
    public TarjetaCreditoDto findByNumeroTarjeta(String numeroTarjeta) {
        return tarjetaCreditoRepository.findByNumeroTarjeta(numeroTarjeta)
                .map(TarjetaCreditoMapper.getInstance()::fromEntityToModel)
                .map(TarjetaCreditoMapper.getInstance()::fromModelToDto).orElseThrow(
                        () -> new ResourceNotFoundException("Tarjeta con número: " + numeroTarjeta + " no encontrada"));
    }
}
