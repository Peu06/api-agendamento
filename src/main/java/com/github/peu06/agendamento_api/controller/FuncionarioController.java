package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Funcionario;
import com.github.peu06.agendamento_api.service.FuncionarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> getAll(){
        return funcionarioService.getAll();
    }

    @PostMapping
    public Funcionario create(@RequestBody Funcionario funcionario) {
        return funcionarioService.save(funcionario);
    }

    @PutMapping
    public Funcionario update(@PathVariable Long id, @RequestBody Funcionario funcionario, @RequestParam Long estabelecimentoLogadoId) {
        return funcionarioService.update(id, funcionario, estabelecimentoLogadoId);
    }
}
