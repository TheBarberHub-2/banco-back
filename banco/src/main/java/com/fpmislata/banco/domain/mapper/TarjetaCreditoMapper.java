package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.TarjetaCredito;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.TarjetaCreditoEntity;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.service.dto.TarjetaCreditoDto;

public class TarjetaCreditoMapper {
    private static TarjetaCreditoMapper instance;

    private TarjetaCreditoMapper() {
    }

    public static TarjetaCreditoMapper getInstance() {
        if (instance == null) {
            instance = new TarjetaCreditoMapper();
        }
        return instance;
    }

    public TarjetaCredito fromEntityToModel(TarjetaCreditoEntity tarjetaEntity) {
        if (tarjetaEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = tarjetaEntity.cuenta().cliente();
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
        CuentaBancariaEntity cuentaEntity = tarjetaEntity.cuenta();
        CuentaBancaria cuenta = null;
        if (cuentaEntity != null) {
            cuenta = new CuentaBancaria(
                    cuentaEntity.id(),
                    cliente,
                    cuentaEntity.saldo(),
                    cuentaEntity.iban(),
                    null,
                    null);
        }
        return new TarjetaCredito(
                tarjetaEntity.id(),
                cuenta,
                tarjetaEntity.numeroTarjeta(),
                tarjetaEntity.fechaCaducidad(),
                tarjetaEntity.cvc(),
                tarjetaEntity.nombreCompleto());
    }

    public TarjetaCreditoEntity fromModelToEntity(TarjetaCredito tarjeta) {
        if (tarjeta == null) {
            return null;
        }
        Cliente cliente = tarjeta.getCuenta().getCliente();
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
        CuentaBancaria cuenta = tarjeta.getCuenta();
        CuentaBancariaEntity cuentaEntity = null;
        if (cuenta != null) {
            cuentaEntity = new CuentaBancariaEntity(
                    cuenta.getId(),
                    clienteEntity,
                    cuenta.getIban(),
                    cuenta.getSaldo(),
                    null,
                    null);
        }
        return new TarjetaCreditoEntity(
                tarjeta.getId(),
                cuentaEntity,
                tarjeta.getNumeroTarjeta(),
                tarjeta.getFechaCaducidad(),
                tarjeta.getCvc(),
                tarjeta.getNombreCompleto());
    }

    public TarjetaCreditoDto fromModelToDto(TarjetaCredito tarjeta) {
        if (tarjeta == null) {
            return null;
        }

        Cliente cliente = tarjeta.getCuenta().getCliente();
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
        CuentaBancaria cuenta = tarjeta.getCuenta();
        CuentaBancariaDto cuentaDto = null;
        if (cuenta != null) {
            cuentaDto = new CuentaBancariaDto(
                    cuenta.getId(),
                    clienteDto,
                    cuenta.getSaldo(),
                    cuenta.getIban(),
                    null,
                    null);
        }

        return new TarjetaCreditoDto(
                tarjeta.getId(),
                cuentaDto,
                tarjeta.getNumeroTarjeta(),
                tarjeta.getFechaCaducidad(),
                tarjeta.getCvc(),
                tarjeta.getNombreCompleto());
    }

    public TarjetaCredito fromDtoToModel(TarjetaCreditoDto dto) {
        if (dto == null) {
            return null;
        }
        ClienteDto clienteDto = dto.cuenta().cliente();
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
        CuentaBancariaDto cuentaDto = dto.cuenta();
        CuentaBancaria cuenta = null;
        if (cuentaDto != null) {
            cuenta = new CuentaBancaria(
                    cuentaDto.id(),
                    cliente,
                    cuentaDto.saldo(),
                    cuentaDto.iban(),
                    null,
                    null);
        }
        return new TarjetaCredito(
                dto.id(),
                cuenta,
                dto.numeroTarjeta(),
                dto.fechaCaducidad(),
                dto.cvc(),
                dto.nombreCompleto());
    }
}
