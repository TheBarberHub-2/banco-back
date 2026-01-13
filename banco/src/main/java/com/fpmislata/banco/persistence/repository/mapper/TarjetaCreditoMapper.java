package com.fpmislata.banco.persistence.repository.mapper;

import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.ClienteJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.CuentaBancariaJpaEntity;
import com.fpmislata.banco.persistence.dao.jpa.entity.TarjetaCreditoJpaEntity;

public class TarjetaCreditoMapper {
    private static TarjetaCreditoMapper INSTANCE;

    private TarjetaCreditoMapper() {
    }

    public static TarjetaCreditoMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TarjetaCreditoMapper();
        }
        return INSTANCE;
    }

    public TarjetaCreditoJpaEntity fromEntityToJpa(TarjetaCreditoEntity tarjetaCreditoEntity) {
        if (tarjetaCreditoEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = tarjetaCreditoEntity.cuenta().cliente();
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
        CuentaBancariaEntity cuentaBancariaEntity = tarjetaCreditoEntity.cuenta();
        CuentaBancariaJpaEntity cuentaBancariaJpaEntity = null;
        if (cuentaBancariaEntity != null) {
            cuentaBancariaJpaEntity = new CuentaBancariaJpaEntity(
                    cuentaBancariaEntity.id(),
                    clienteJpaEntity,
                    cuentaBancariaEntity.iban(),
                    cuentaBancariaEntity.saldo(),
                    null,
                    null);
        }
        return new TarjetaCreditoJpaEntity(
                tarjetaCreditoEntity.id(),
                cuentaBancariaJpaEntity,
                tarjetaCreditoEntity.numeroTarjeta(),
                tarjetaCreditoEntity.fechaCaducidad(),
                tarjetaCreditoEntity.cvc(),
                tarjetaCreditoEntity.nombreCompleto());
    }

    public TarjetaCreditoEntity fromJpaToEntity(TarjetaCreditoJpaEntity tarjetaCreditoJpaEntity) {
        if (tarjetaCreditoJpaEntity == null) {
            return null;
        }
        ClienteJpaEntity clienteJpaEntity = tarjetaCreditoJpaEntity.getCuentaBancaria().getCliente();
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
        CuentaBancariaJpaEntity cuentaBancariaJpaEntity = tarjetaCreditoJpaEntity.getCuentaBancaria();
        CuentaBancariaEntity cuentaBancariaEntity = null;
        if (cuentaBancariaJpaEntity != null) {
            cuentaBancariaEntity = new CuentaBancariaEntity(
                    cuentaBancariaJpaEntity.getId(),
                    clienteEntity,
                    cuentaBancariaJpaEntity.getIban(),
                    cuentaBancariaJpaEntity.getSaldo(),
                    null,
                    null);
        }
        return new TarjetaCreditoEntity(
                tarjetaCreditoJpaEntity.getId(),
                cuentaBancariaEntity,
                tarjetaCreditoJpaEntity.getNumeroTarjeta(),
                tarjetaCreditoJpaEntity.getFechaCaducidad(),
                tarjetaCreditoJpaEntity.getCvc(),
                tarjetaCreditoJpaEntity.getNombreCompleto());
    }
}
