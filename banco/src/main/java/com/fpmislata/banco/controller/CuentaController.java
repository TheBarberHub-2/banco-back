package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.mapper.CuentaMapper;
import com.fpmislata.banco.controller.webModel.request.CuentaRequest;
import com.fpmislata.banco.controller.webModel.response.CuentaDetailResponse;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.CuentaService;
import com.fpmislata.banco.domain.service.dto.CuentaDto;
import com.fpmislata.banco.domain.validation.spring_validator.DtoValidator;
import com.fpmislata.banco.enums.TipoCuenta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<Page<CuentaDetailResponse>> findAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<CuentaDto> cuentaPage = cuentaService.findAll(page, size);

        List<CuentaDetailResponse> responses = cuentaPage.data().stream()
                .map(CuentaMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<CuentaDetailResponse> responsePage = new Page<>(
                responses,
                cuentaPage.pageNumber(),
                cuentaPage.pageSize(),
                cuentaPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/empresas")
    public ResponseEntity<Page<CuentaDetailResponse>> findEmpresas(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<CuentaDto> cuentaPage = cuentaService.findByTipoCuenta(TipoCuenta.EMPRESA, page, size);

        List<CuentaDetailResponse> responses = cuentaPage.data().stream()
                .map(CuentaMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<CuentaDetailResponse> responsePage = new Page<>(
                responses,
                cuentaPage.pageNumber(),
                cuentaPage.pageSize(),
                cuentaPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<Page<CuentaDetailResponse>> findClientes(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<CuentaDto> cuentaPage = cuentaService.findByTipoCuenta(TipoCuenta.PERSONAL, page, size);

        List<CuentaDetailResponse> responses = cuentaPage.data().stream()
                .map(CuentaMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<CuentaDetailResponse> responsePage = new Page<>(
                responses,
                cuentaPage.pageNumber(),
                cuentaPage.pageSize(),
                cuentaPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDetailResponse> findById(@PathVariable Long id) {
        CuentaDetailResponse response = CuentaMapper.getInstance()
                .fromDtoToDetail(cuentaService.getById(id));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CuentaDetailResponse> create(@RequestBody CuentaRequest request) {
        CuentaDto dto = CuentaMapper.getInstance().fromRequestToDto(request);
        DtoValidator.validate(dto);

        CuentaDetailResponse response = CuentaMapper.getInstance()
                .fromDtoToDetail(cuentaService.create(dto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDetailResponse> update(
            @PathVariable Long id,
            @RequestBody CuentaRequest request) {

        CuentaDto dto = CuentaMapper.getInstance().fromRequestToDto(request);
        dto = new CuentaDto(id, dto.numeroCuenta(), dto.tipoCuenta(), dto.saldo(), dto.titular());

        DtoValidator.validate(dto);

        CuentaDetailResponse response = CuentaMapper.getInstance()
                .fromDtoToDetail(cuentaService.update(dto));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuentaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
