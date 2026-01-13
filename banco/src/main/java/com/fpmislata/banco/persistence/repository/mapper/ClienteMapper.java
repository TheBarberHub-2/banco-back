package com.fpmislata.banco.persistence.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;

public class ClienteMapper {

    private static ClienteMapper INSTANCE;

    private ClienteMapper() {
    }

    public static ClienteMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClienteMapper();
        }
        return INSTANCE;
    }

    public ClienteJpaEntity fromEntityToJpa(ClienteEntity clienteEntity) {
        if (clienteEntity == null) {
            return null;
        }
        List<CuentaBancariaJpaEntity> cuentas = new ArrayList<>();
        if (clienteEntity.cuentas() != null && !clienteEntity.cuentas().isEmpty()) {
            cuentas = clienteEntity.cuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromEntityToJpa)
                    .toList();
        }
        return new ClienteJpaEntity(
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

    public ClienteEntity fromJpaToEntity(ClienteJpaEntity clienteJpaEntity) {
        if (clienteJpaEntity == null) {
            return null;
        }
        List<CuentaBancariaEntity> cuentas = new ArrayList<>();
        if (clienteJpaEntity.getCuentas() != null && !clienteJpaEntity.getCuentas().isEmpty()) {
            cuentas = clienteJpaEntity.getCuentas().stream()
                    .map(CuentaBancariaMapper.getInstance()::fromJpaToEntity)
                    .toList();
        }
        return new ClienteEntity(
                clienteJpaEntity.getId(),
                clienteJpaEntity.getLogin(),
                clienteJpaEntity.getPassword(),
                clienteJpaEntity.getNombre(),
                clienteJpaEntity.getApellido1(),
                clienteJpaEntity.getApellido2(),
                clienteJpaEntity.getDni(),
                clienteJpaEntity.getApiToken(),
                cuentas);
    }
}
