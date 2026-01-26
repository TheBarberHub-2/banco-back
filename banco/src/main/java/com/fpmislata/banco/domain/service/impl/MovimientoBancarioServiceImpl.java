package com.fpmislata.banco.domain.service.impl;

import java.util.List;

import com.fpmislata.banco.domain.mapper.MovimientoBancarioMapper;
import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

public class MovimientoBancarioServiceImpl implements MovimientoBancarioService {

    private final MovimientoBancarioRepository movimientoBancarioRepository;

    public MovimientoBancarioServiceImpl(MovimientoBancarioRepository movimientoBancarioRepository) {
        this.movimientoBancarioRepository = movimientoBancarioRepository;
    }

    @Override
    public List<MovimientoBancarioDto> findByCuenta(long cuentaId) {
        List<MovimientoBancarioEntity> movimientoBancarioEntities = movimientoBancarioRepository.findByCuenta(cuentaId);

        List<MovimientoBancarioDto> movimientoBancarioDto = movimientoBancarioEntities.stream()
                .map(MovimientoBancarioMapper.getInstance()::fromEntityToModel)
                .map(MovimientoBancarioMapper.getInstance()::fromModelToDto)
                .toList();

        return movimientoBancarioDto;
    }

    @Override
    public List<MovimientoBancarioDto> findByTarjeta(long tarjetaId) {
        List<MovimientoBancarioEntity> movimientoBancarioEntities = movimientoBancarioRepository
                .findByTarjeta(tarjetaId);

        List<MovimientoBancarioDto> movimientoBancarioDto = movimientoBancarioEntities.stream()
                .map(MovimientoBancarioMapper.getInstance()::fromEntityToModel)
                .map(MovimientoBancarioMapper.getInstance()::fromModelToDto)
                .toList();

        return movimientoBancarioDto;
    }

    @Override
    public MovimientoBancarioDto insert(MovimientoBancarioDto movimientoBancarioDto) {
        MovimientoBancarioEntity movimientoBancarioEntity = MovimientoBancarioMapper.getInstance()
                .fromModelToEntity(MovimientoBancarioMapper.getInstance().fromDtoToModel(movimientoBancarioDto));
        MovimientoBancarioEntity insertedEntity = movimientoBancarioRepository.insert(movimientoBancarioEntity);
        return MovimientoBancarioMapper.getInstance()
                .fromModelToDto(MovimientoBancarioMapper.getInstance().fromEntityToModel(insertedEntity));
    }
}