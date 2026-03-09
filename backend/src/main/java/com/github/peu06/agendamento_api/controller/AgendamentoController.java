package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Agendamento;
import com.github.peu06.agendamento_api.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public List<Agendamento> getAll(){return agendamentoService.getAll();}

    @PostMapping
    public Agendamento create(@RequestBody Agendamento agendamento){
        return agendamentoService.save(agendamento);
    }
}
