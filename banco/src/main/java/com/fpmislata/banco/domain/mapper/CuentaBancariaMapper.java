package com.fpmislata.banco.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.model.TarjetaCredito;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

public class CuentaBancariaMapper {
    private static CuentaBancariaMapper instance;

    private CuentaBancariaMapper() {
    }

    public static CuentaBancariaMapper getInstance() {
        if (instance == null) {
            instance = new CuentaBancariaMapper();
        }
        return instance;
    }

    public CuentaBancaria fromEntityToModel(CuentaBancariaEntity cuentaEntity) {
        if (cuentaEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = cuentaEntity.cliente();
        Cliente cliente = null;
        if (clienteEntity != null) {
            cliente = new Cliente(
                    clienteEntity.id(),
                    clienteEntity.login(),
                    clienteEntity.password(),
                    clienteEntity.nombre(),
                    clienteEntity.apellido1(),
                    clienteEntity.apellido2(),
                    clienteEntity.dni(),
                    clienteEntity.apiToken(),
                    null);
        }

        List<TarjetaCredito> tarjetas = new ArrayList<>();
        if (cuentaEntity.tarjetas() != null && !cuentaEntity.tarjetas().isEmpty()) {
            tarjetas = cuentaEntity.tarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromEntityToModel)
                    .toList();
        }
        List<MovimientoBancario> movimientos = new ArrayList<>();
        if (cuentaEntity.movimientos() != null && !cuentaEntity.movimientos().isEmpty()) {
            movimientos = cuentaEntity.movimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromEntityToModel)
                    .toList();
        }
        return new CuentaBancaria(
                cuentaEntity.id(),
                cliente,
                cuentaEntity.saldo(),
                cuentaEntity.iban(),
                tarjetas,
                movimientos);
    }

    public CuentaBancariaEntity fromModelToEntity(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return null;
        }
        Cliente cliente = cuenta.getCliente();
        ClienteEntity clienteEntity = null;
        if (cliente != null) {
            clienteEntity = new ClienteEntity(
                    cliente.getId(),
                    cliente.getLogin(),
                    cliente.getPassword(),
                    cliente.getNombre(),
                    cliente.getApellido1(),
                    cliente.getApellido2(),
                    cliente.getDni(),
                    cliente.getApiToken(),
                    null);
        }

        List<TarjetaCreditoEntity> tarjetas = new ArrayList<>();
        if (cuenta.getTarjetas() != null && !cuenta.getTarjetas().isEmpty()) {
            tarjetas = cuenta.getTarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromModelToEntity)
                    .toList();
        }
        List<MovimientoBancarioEntity> movimientos = new ArrayList<>();
        if (cuenta.getMovimientos() != null && !cuenta.getMovimientos().isEmpty()) {
            movimientos = cuenta.getMovimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromModelToEntity)
                    .toList();
        }
        return new CuentaBancariaEntity(
                cuenta.getId(),
                clienteEntity,
                cuenta.getIban(),
                cuenta.getSaldo(),
                tarjetas,
                movimientos);
    }

    public CuentaBancariaDto fromModelToDto(CuentaBancaria cuenta) {
        if (cuenta == null) {
            return null;
        }
        Cliente cliente = cuenta.getCliente();
        ClienteDto clienteDto = null;
        if (cliente != null) {
            clienteDto = new ClienteDto(
                    cliente.getId(),
                    cliente.getLogin(),
                    cliente.getPassword(),
                    cliente.getNombre(),
                    cliente.getApellido1(),
                    cliente.getApellido2(),
                    cliente.getDni(),
                    cliente.getApiToken(),
                    null);
        }

        List<TarjetaCreditoDto> tarjetas = new ArrayList<>();
        if (cuenta.getTarjetas() != null && !cuenta.getTarjetas().isEmpty()) {
            tarjetas = cuenta.getTarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromModelToDto)
                    .toList();
        }
        List<MovimientoBancarioDto> movimientos = new ArrayList<>();
        if (cuenta.getMovimientos() != null && !cuenta.getMovimientos().isEmpty()) {
            movimientos = cuenta.getMovimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromModelToDto)
                    .toList();
        }
        return new CuentaBancariaDto(
                cuenta.getId(),
                clienteDto,
                cuenta.getSaldo(),
                cuenta.getIban(),
                tarjetas,
                movimientos);
    }

    public CuentaBancaria fromDtoToModel(CuentaBancariaDto dto) {
        if (dto == null) {
            return null;
        }
        ClienteDto clienteDto = dto.cliente();
        Cliente cliente = null;
        if (clienteDto != null) {
            cliente = new Cliente(
                    clienteDto.id(),
                    clienteDto.login(),
                    clienteDto.password(),
                    clienteDto.nombre(),
                    clienteDto.apellido1(),
                    clienteDto.apellido2(),
                    clienteDto.dni(),
                    clienteDto.apiToken(),
                    null);
        }
        List<TarjetaCredito> tarjetas = new ArrayList<>();
        if (dto.tarjetas() != null && !dto.tarjetas().isEmpty()) {
            tarjetas = dto.tarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromDtoToModel)
                    .toList();
        }
        List<MovimientoBancario> movimientos = new ArrayList<>();
        if (dto.movimientos() != null && !dto.movimientos().isEmpty()) {
            movimientos = dto.movimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromDtoToModel)
                    .toList();
        }
        return new CuentaBancaria(
                dto.id(),
                cliente,
                dto.saldo(),
                dto.iban(),
                tarjetas,
                movimientos);
    }
}
