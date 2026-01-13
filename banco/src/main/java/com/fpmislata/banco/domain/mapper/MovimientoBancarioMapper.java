package com.fpmislata.banco.domain.mapper;

import com.fpmislata.banco.domain.model.Cliente;
import com.fpmislata.banco.domain.model.CuentaBancaria;
import com.fpmislata.banco.domain.model.MovimientoBancario;
import com.fpmislata.banco.domain.repository.entity.ClienteEntity;
import com.fpmislata.banco.domain.repository.entity.CuentaBancariaEntity;
import com.fpmislata.banco.domain.repository.entity.MovimientoBancarioEntity;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;

public class MovimientoBancarioMapper {
    private static MovimientoBancarioMapper instance;

    private MovimientoBancarioMapper() {
    }

    public static MovimientoBancarioMapper getInstance() {
        if (instance == null) {
            instance = new MovimientoBancarioMapper();
        }
        return instance;
    }

    public MovimientoBancario fromEntityToModel(MovimientoBancarioEntity movimientoEntity) {
        if (movimientoEntity == null) {
            return null;
        }
        ClienteEntity clienteEntity = movimientoEntity.cuentaBancaria().cliente();
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
        CuentaBancariaEntity cuentaBancariaEntity = movimientoEntity.cuentaBancaria();
        CuentaBancaria cuentaBancaria = null;
        if (cuentaBancariaEntity != null) {
            cuentaBancaria = new CuentaBancaria(
                    cuentaBancariaEntity.id(),
                    cliente,
                    cuentaBancariaEntity.saldo(),
                    cuentaBancariaEntity.iban(),
                    null,
                    null);
        }
        return new MovimientoBancario(
                movimientoEntity.id(),
                cuentaBancaria,
                movimientoEntity.tipoMovimientoBancario(),
                movimientoEntity.origenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromEntityToModel(movimientoEntity.tarjetaCreditoOrigen()),
                movimientoEntity.fecha(),
                movimientoEntity.importe(),
                movimientoEntity.concepto());
    }

    public MovimientoBancarioEntity fromModelToEntity(MovimientoBancario movimiento) {
        if (movimiento == null) {
            return null;
        }
        Cliente cliente = movimiento.getCuentaBancaria().getCliente();
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

        CuentaBancaria cuentaBancaria = movimiento.getCuentaBancaria();
        CuentaBancariaEntity cuentaBancariaEntity = null;
        if (cuentaBancaria != null) {
            cuentaBancariaEntity = new CuentaBancariaEntity(
                    cuentaBancaria.getId(),
                    clienteEntity,
                    cuentaBancaria.getIban(),
                    cuentaBancaria.getSaldo(),
                    null,
                    null);
        }
        return new MovimientoBancarioEntity(
                movimiento.getId(),
                cuentaBancariaEntity,
                movimiento.getTipoMovimientoBancario(),
                movimiento.getOrigenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromModelToEntity(movimiento.getTarjetaCreditoOrigen()),
                movimiento.getFecha(),
                movimiento.getImporte(),
                movimiento.getConcepto());
    }

    public MovimientoBancarioDto fromModelToDto(MovimientoBancario movimiento) {
        if (movimiento == null) {
            return null;
        }
        Cliente cliente = movimiento.getCuentaBancaria().getCliente();
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
        CuentaBancaria cuentaBancaria = movimiento.getCuentaBancaria();
        CuentaBancariaDto cuentaBancariaDto = null;
        if (cuentaBancaria != null) {
            cuentaBancariaDto = new CuentaBancariaDto(
                    cuentaBancaria.getId(),
                    clienteDto,
                    cuentaBancaria.getSaldo(),
                    cuentaBancaria.getIban(),
                    null,
                    null);
        }
        return new MovimientoBancarioDto(
                movimiento.getId(),
                cuentaBancariaDto,
                movimiento.getTipoMovimientoBancario(),
                movimiento.getOrigenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromModelToDto(movimiento.getTarjetaCreditoOrigen()),
                movimiento.getFecha(),
                movimiento.getImporte(),
                movimiento.getConcepto());
    }

    public MovimientoBancario fromDtoToModel(MovimientoBancarioDto dto) {
        if (dto == null) {
            return null;
        }
        ClienteDto clienteDto = dto.cuentaBancaria().cliente();
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
        CuentaBancariaDto cuentaBancariaDto = dto.cuentaBancaria();
        CuentaBancaria cuentaBancaria = null;
        if (cuentaBancariaDto != null) {
            cuentaBancaria = new CuentaBancaria(
                    cuentaBancariaDto.id(),
                    cliente,
                    cuentaBancariaDto.saldo(),
                    cuentaBancariaDto.iban(),
                    null,
                    null);
        }
        return new MovimientoBancario(
                dto.id(),
                cuentaBancaria,
                dto.tipoMovimientoBancario(),
                dto.origenMovimientoBancario(),
                TarjetaCreditoMapper.getInstance().fromDtoToModel(dto.tarjetaCreditoOrigen()),
                dto.fecha(),
                dto.importe(),
                dto.concepto());
    }
}
