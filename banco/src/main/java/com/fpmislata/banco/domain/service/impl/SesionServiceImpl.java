package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.SesionMapper;
import com.fpmislata.banco.domain.repository.SesionRepository;
import com.fpmislata.banco.domain.repository.entity.SesionEntity;
import com.fpmislata.banco.domain.service.SesionService;
import com.fpmislata.banco.domain.service.dto.SesionDto;
import com.fpmislata.banco.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

public class SesionServiceImpl implements SesionService {
    private final SesionRepository sesionRepository;

    public SesionServiceImpl(SesionRepository sesionRepository) {
        this.sesionRepository = sesionRepository;
    }

    @Override
    public SesionDto getByToken(String token) {
        return sesionRepository.findByToken(token).map(SesionMapper.getInstance()::fromEntityToModel)
                .map(SesionMapper.getInstance()::fromModelToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Sesión no iniciada"));
    }

    @Override
    @Transactional
    public SesionDto create(SesionDto sesionDto) {

        SesionEntity sesionEntity = SesionMapper.getInstance()
                .fromModelToEntity(SesionMapper.getInstance().fromDtoToModel(sesionDto));

        return SesionMapper.getInstance()
                .fromModelToDto(SesionMapper.getInstance().fromEntityToModel(sesionRepository.save(sesionEntity)));

    }

    @Override
    @Transactional
    public void deleteByToken(String token) {
        sesionRepository.deleteByToken(token);
    }
}
