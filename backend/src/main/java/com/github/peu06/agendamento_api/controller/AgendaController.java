package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Agenda;
import com.github.peu06.agendamento_api.service.AgendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public List<Agenda> getAll(){return agendaService.getAll();}

    @PostMapping
    public Agenda create(@RequestBody Agenda agenda){
        return agendaService.save(agenda);
    }
}
