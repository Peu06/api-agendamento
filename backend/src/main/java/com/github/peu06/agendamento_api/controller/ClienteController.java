package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Cliente;
import com.github.peu06.agendamento_api.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll() {return clienteService.getAll();}

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.update(id, cliente);
    }
}
