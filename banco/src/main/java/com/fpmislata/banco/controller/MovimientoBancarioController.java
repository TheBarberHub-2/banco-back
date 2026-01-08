package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.mapper.MovimientoBancarioMapper;
import com.fpmislata.banco.controller.webModel.request.MovimientoBancarioRequest;
import com.fpmislata.banco.controller.webModel.response.MovimientoBancarioDetailResponse;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.MovimientoBancarioService;
import com.fpmislata.banco.domain.service.dto.MovimientoBancarioDto;
import com.fpmislata.banco.domain.validation.spring_validator.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoBancarioController {

    private final MovimientoBancarioService movimientoService;

    public MovimientoBancarioController(MovimientoBancarioService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping
    public ResponseEntity<MovimientoBancarioDetailResponse> create(@RequestBody MovimientoBancarioRequest request) {
        MovimientoBancarioDto dto = MovimientoBancarioMapper.getInstance().fromRequestToDto(request);
        DtoValidator.validate(dto);

        MovimientoBancarioDetailResponse response = MovimientoBancarioMapper.getInstance()
                .fromDtoToDetail(movimientoService.create(dto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<MovimientoBancarioDetailResponse>> findAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<MovimientoBancarioDto> movimientoPage = movimientoService.findAll(page, size);

        List<MovimientoBancarioDetailResponse> responses = movimientoPage.data().stream()
                .map(MovimientoBancarioMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<MovimientoBancarioDetailResponse> responsePage = new Page<>(
                responses,
                movimientoPage.pageNumber(),
                movimientoPage.pageSize(),
                movimientoPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoBancarioDetailResponse> findById(@PathVariable Long id) {
        return movimientoService.findById(id)
                .map(MovimientoBancarioMapper.getInstance()::fromDtoToDetail)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
