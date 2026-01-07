package com.fpmislata.banco.domain.service.impl;

import com.fpmislata.banco.domain.mapper.TransferenciaMapper;
import com.fpmislata.banco.domain.model.Cuenta;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.model.Transferencia;
import com.fpmislata.banco.domain.repository.CuentaRepository;
import com.fpmislata.banco.domain.repository.TransferenciaRepository;
import com.fpmislata.banco.domain.service.TransferenciaService;
import com.fpmislata.banco.domain.service.dto.TransferenciaDto;
import com.fpmislata.banco.exception.BusinessException;
import com.fpmislata.banco.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final CuentaRepository cuentaRepository;

    public TransferenciaServiceImpl(TransferenciaRepository transferenciaRepository,
            CuentaRepository cuentaRepository) {
        this.transferenciaRepository = transferenciaRepository;
        this.cuentaRepository = cuentaRepository;
    }

    @Override
    public TransferenciaDto ejecutarTransferencia(TransferenciaDto transferenciaDto) {
        // Validar que las cuentas existen
        Cuenta cuentaOrigen = cuentaRepository.findByNumeroCuenta(transferenciaDto.cuentaOrigen())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuenta de origen no encontrada: " + transferenciaDto.cuentaOrigen()));

        Cuenta cuentaDestino = cuentaRepository.findByNumeroCuenta(transferenciaDto.cuentaDestino())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cuenta de destino no encontrada: " + transferenciaDto.cuentaDestino()));

        // Validar que la cuenta origen tiene saldo suficiente
        if (cuentaOrigen.getSaldo() < transferenciaDto.monto()) {
            throw new BusinessException("Saldo insuficiente en la cuenta de origen");
        }

        // Validar que no sean la misma cuenta
        if (transferenciaDto.cuentaOrigen().equals(transferenciaDto.cuentaDestino())) {
            throw new BusinessException("No se puede transferir a la misma cuenta");
        }

        // Realizar la transferencia
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - transferenciaDto.monto());
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + transferenciaDto.monto());

        // Guardar las cuentas actualizadas
        cuentaRepository.save(cuentaOrigen);
        cuentaRepository.save(cuentaDestino);

        // Registrar la transferencia
        Transferencia transferencia = TransferenciaMapper.getInstance().fromDtoToModel(transferenciaDto);
        transferencia.setFecha(LocalDateTime.now());
        Transferencia saved = transferenciaRepository.save(transferencia);

        return TransferenciaMapper.getInstance().fromModelToDto(saved);
    }

    @Override
    public Page<TransferenciaDto> findAll(int page, int size) {
        Page<Transferencia> transferenciaPage = transferenciaRepository.findAll(page, size);
        List<TransferenciaDto> dtos = transferenciaPage.data().stream()
                .map(TransferenciaMapper.getInstance()::fromModelToDto)
                .toList();
        return new Page<>(dtos, transferenciaPage.pageNumber(),
                transferenciaPage.pageSize(), transferenciaPage.totalElements());
    }

    @Override
    public Optional<TransferenciaDto> findById(long id) {
        return transferenciaRepository.findById(id)
                .map(TransferenciaMapper.getInstance()::fromModelToDto);
    }
}
