package com.fpmislata.banco.persistence.repository.mapper;

import java.util.ArrayList;
import java.util.List;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.MovimientoBancarioJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public class CuentaBancariaMapper {

    private static CuentaBancariaMapper INSTANCE;

    private CuentaBancariaMapper() {
    }

    public static CuentaBancariaMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CuentaBancariaMapper();
        }
        return INSTANCE;
    }

    public CuentaBancariaJpaEntity fromEntityToJpa(CuentaBancariaEntity cuentaBancariaEntity) {
        if (cuentaBancariaEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = cuentaBancariaEntity.cliente();
        ClienteJpaEntity clienteJpaEntity = null;
        if (clienteEntity != null) {
            clienteJpaEntity = new ClienteJpaEntity(
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
        List<TarjetaCreditoJpaEntity> tarjetas = new ArrayList<>();
        if (cuentaBancariaEntity.tarjetas() != null && !cuentaBancariaEntity.tarjetas().isEmpty()) {
            tarjetas = cuentaBancariaEntity.tarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromEntityToJpa)
                    .toList();
        }
        List<MovimientoBancarioJpaEntity> movimientos = new ArrayList<>();
        if (cuentaBancariaEntity.movimientos() != null && !cuentaBancariaEntity.movimientos().isEmpty()) {
            movimientos = cuentaBancariaEntity.movimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromEntityToJpa)
                    .toList();
        }
        return new CuentaBancariaJpaEntity(
                cuentaBancariaEntity.id(),
                clienteJpaEntity,
                cuentaBancariaEntity.iban(),
                cuentaBancariaEntity.saldo(),
                tarjetas,
                movimientos);
    }

    public CuentaBancariaEntity fromJpaToEntity(CuentaBancariaJpaEntity cuentaBancariaJpaEntity) {
        if (cuentaBancariaJpaEntity == null) {
            return null;
        }
        ClienteJpaEntity clienteJpaEntity = cuentaBancariaJpaEntity.getCliente();
        ClienteEntity clienteEntity = null;
        if (clienteJpaEntity != null) {
            clienteEntity = new ClienteEntity(
                    clienteJpaEntity.getId(),
                    clienteJpaEntity.getLogin(),
                    clienteJpaEntity.getPassword(),
                    clienteJpaEntity.getNombre(),
                    clienteJpaEntity.getApellido1(),
                    clienteJpaEntity.getApellido2(),
                    clienteJpaEntity.getDni(),
                    clienteJpaEntity.getApiToken(),
                    null);
        }
        List<TarjetaCreditoEntity> tarjetas = new ArrayList<>();
        if (cuentaBancariaJpaEntity.getTarjetas() != null && !cuentaBancariaJpaEntity.getTarjetas().isEmpty()) {
            tarjetas = cuentaBancariaJpaEntity.getTarjetas().stream()
                    .map(TarjetaCreditoMapper.getInstance()::fromJpaToEntity)
                    .toList();
        }
        List<MovimientoBancarioEntity> movimientos = new ArrayList<>();
        if (cuentaBancariaJpaEntity.getMovimientos() != null && !cuentaBancariaJpaEntity.getMovimientos().isEmpty()) {
            movimientos = cuentaBancariaJpaEntity.getMovimientos().stream()
                    .map(MovimientoBancarioMapper.getInstance()::fromJpaToEntity)
                    .toList();
        }
        return new CuentaBancariaEntity(
                cuentaBancariaJpaEntity.getId(),
                clienteEntity,
                cuentaBancariaJpaEntity.getIban(),
                cuentaBancariaJpaEntity.getSaldo(),
                tarjetas,
                movimientos);
    }
}
