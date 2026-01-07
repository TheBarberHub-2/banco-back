package com.fpmislata.banco.controller;

import com.fpmislata.banco.controller.mapper.TransferenciaMapper;
import com.fpmislata.banco.controller.webModel.request.TransferenciaRequest;
import com.fpmislata.banco.controller.webModel.response.TransferenciaDetailResponse;
import com.fpmislata.banco.domain.model.Page;
import com.fpmislata.banco.domain.service.TransferenciaService;
import com.fpmislata.banco.domain.service.dto.TransferenciaDto;
import com.fpmislata.banco.domain.validation.spring_validator.DtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    private final TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping
    public ResponseEntity<TransferenciaDetailResponse> ejecutarTransferencia(
            @RequestBody TransferenciaRequest request) {

        TransferenciaDto dto = TransferenciaMapper.getInstance().fromRequestToDto(request);
        DtoValidator.validate(dto);

        TransferenciaDetailResponse response = TransferenciaMapper.getInstance()
                .fromDtoToDetail(transferenciaService.ejecutarTransferencia(dto));

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<TransferenciaDetailResponse>> findAll(
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        Page<TransferenciaDto> transferenciaPage = transferenciaService.findAll(page, size);

        List<TransferenciaDetailResponse> responses = transferenciaPage.data().stream()
                .map(TransferenciaMapper.getInstance()::fromDtoToDetail)
                .toList();

        Page<TransferenciaDetailResponse> responsePage = new Page<>(
                responses,
                transferenciaPage.pageNumber(),
                transferenciaPage.pageSize(),
                transferenciaPage.totalElements());

        return new ResponseEntity<>(responsePage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferenciaDetailResponse> findById(@PathVariable Long id) {
        return transferenciaService.findById(id)
                .map(dto -> TransferenciaMapper.getInstance().fromDtoToDetail(dto))
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
