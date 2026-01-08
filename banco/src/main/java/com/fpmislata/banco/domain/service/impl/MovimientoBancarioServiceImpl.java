package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.MovimientoBancarioMapper;
import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.repository.MovimientoBancarioRepository;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoBancarioServiceImpl implements MovimientoBancarioService {

    private final MovimientoBancarioRepository movimientoRepository;

    public MovimientoBancarioServiceImpl(MovimientoBancarioRepository movimientoRepository) {
        this.movimientoRepository = movimientoRepository;
    }

    @Override
    public MovimientoBancarioDto create(MovimientoBancarioDto movimientoDto) {
        MovimientoBancario movimiento = MovimientoBancarioMapper.getInstance().fromDtoToModel(movimientoDto);
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(new Date());
        }
        MovimientoBancario saved = movimientoRepository.save(movimiento);
        return MovimientoBancarioMapper.getInstance().fromModelToDto(saved);
    }

    @Override
    public Page<MovimientoBancarioDto> findAll(int page, int size) {
        Page<MovimientoBancario> movimientoPage = movimientoRepository.findAll(page, size);
        List<MovimientoBancarioDto> dtos = movimientoPage.data().stream()
                .map(MovimientoBancarioMapper.getInstance()::fromModelToDto)
                .toList();
        return new Page<>(dtos, movimientoPage.pageNumber(),
                movimientoPage.pageSize(), movimientoPage.totalElements());
    }

    @Override
    public Optional<MovimientoBancarioDto> findById(long id) {
        return movimientoRepository.findById(id)
                .map(MovimientoBancarioMapper.getInstance()::fromModelToDto);
    }
}
