package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.mapper.CuentaBancariaMapper;
import com.fpmislata.banco.controller.webModel.request.CuentaBancariaRequest;
import com.fpmislata.banco.controller.webModel.response.CuentaBancariaDetailResponse;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.dto.CuentaBancariaDto;
import com.fpmislata.banco.domain.validation.spring_validator.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaBancariaController {

    private final CuentaBancariaService cuentaService;

    public CuentaBancariaController(CuentaBancariaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<Page<CuentaBancariaDetailResponse>> findAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<CuentaBancariaDto> cuentaPage = cuentaService.findAll(page, size);

        List<CuentaBancariaDetailResponse> responses = cuentaPage.data().stream()
                .map(CuentaBancariaMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<CuentaBancariaDetailResponse> responsePage = new Page<>(
                responses,
                cuentaPage.pageNumber(),
                cuentaPage.pageSize(),
                cuentaPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBancariaDetailResponse> findById(@PathVariable Long id) {
        return cuentaService.findById(id)
                .map(CuentaBancariaMapper.getInstance()::fromDtoToDetail)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CuentaBancariaDetailResponse> create(@RequestBody CuentaBancariaRequest request) {
        CuentaBancariaDto dto = CuentaBancariaMapper.getInstance().fromRequestToDto(request);
        DtoValidator.validate(dto);

        CuentaBancariaDetailResponse response = CuentaBancariaMapper.getInstance()
                .fromDtoToDetail(cuentaService.create(dto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaBancariaDetailResponse> update(
            @PathVariable Long id,
            @RequestBody CuentaBancariaRequest request) {

        CuentaBancariaDto dto = CuentaBancariaMapper.getInstance().fromRequestToDto(request);
        dto = new CuentaBancariaDto(id, dto.saldo(), dto.iban());

        DtoValidator.validate(dto);

        CuentaBancariaDetailResponse response = CuentaBancariaMapper.getInstance()
                .fromDtoToDetail(cuentaService.update(dto));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cuentaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
