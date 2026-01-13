package com.fpmislata.banco.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco.controller.mapper.ClienteMapper;
import com.fpmislata.banco.controller.webModel.response.ClienteDetailResponse;
import com.fpmislata.banco.domain.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<ClienteDetailResponse> getByLogin(@RequestParam String login) {
        ClienteDetailResponse response = ClienteMapper.getInstance()
                .fromDtoToResponse(clienteService.getByLogin(login));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
