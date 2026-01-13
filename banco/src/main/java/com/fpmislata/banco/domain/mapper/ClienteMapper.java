package com.fpmislata.banco.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;

public class ClienteMapper {
    private static ClienteMapper instance;

    private ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (instance == null) {
            instance = new ClienteMapper();
        }
        return instance;
    }

    public Cliente fromEntityToModel(ClienteEntity clienteEntity) {
        if (clienteEntity == null) {
            return null;
        }
        List<CuentaBancaria> cuentas = new ArrayList<>();
        if (clienteEntity.cuentas() != null && !clienteEntity.cuentas().isEmpty()) {
            cuentas = clienteEntity.cuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromEntityToModel)
                    .toList();
        }
        return new Cliente(
                clienteEntity.id(),
                clienteEntity.login(),
                clienteEntity.password(),
                clienteEntity.nombre(),
                clienteEntity.apellido1(),
                clienteEntity.apellido2(),
                clienteEntity.dni(),
                clienteEntity.apiToken(),
                cuentas);
    }

    public ClienteEntity fromModelToEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        List<CuentaBancariaEntity> cuentas = new ArrayList<>();
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            cuentas = cliente.getCuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromModelToEntity)
                    .toList();
        }
        return new ClienteEntity(
                cliente.getId(),
                cliente.getLogin(),
                cliente.getPassword(),
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getDni(),
                cliente.getApiToken(),
                cuentas);
    }

    public ClienteDto fromModelToDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        List<CuentaBancariaDto> cuentas = new ArrayList<>();
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            cuentas = cliente.getCuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromModelToDto)
                    .toList();

        }
        return new ClienteDto(
                cliente.getId(),
                cliente.getLogin(),
                cliente.getPassword(),
                cliente.getNombre(),
                cliente.getApellido1(),
                cliente.getApellido2(),
                cliente.getDni(),
                cliente.getApiToken(),
                cuentas);
    }

    public Cliente fromDtoToModel(ClienteDto dto) {
        if (dto == null) {
            return null;
        }
        List<CuentaBancaria> cuentas = new ArrayList<>();
        if (dto.cuentas() != null && !dto.cuentas().isEmpty()) {
            cuentas = dto.cuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromDtoToModel)
                    .toList();
        }
        return new Cliente(
                dto.id(),
                dto.login(),
                dto.password(),
                dto.nombre(),
                dto.apellido1(),
                dto.apellido2(),
                dto.dni(),
                dto.apiToken(),
                cuentas);
    }
}
