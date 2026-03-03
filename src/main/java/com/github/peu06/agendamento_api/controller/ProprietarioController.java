package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Proprietario;
import com.github.peu06.agendamento_api.service.ProprietarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @GetMapping
    public List<Proprietario> getAll() {return proprietarioService.getAll();}

    @PostMapping
    public Proprietario create(@RequestBody Proprietario proprietario) {
        return proprietarioService.save(proprietario);
    }

    @PutMapping("/{id}")
    public Proprietario update(@PathVariable Long id, @RequestBody Proprietario proprietario) {
        return proprietarioService.update(id, proprietario);
    }
}
