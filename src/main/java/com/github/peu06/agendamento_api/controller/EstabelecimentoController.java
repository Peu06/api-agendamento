package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Estabelecimento;
import com.github.peu06.agendamento_api.service.EstabelecimentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    private final EstabelecimentoService estabelecimentoService;

    public EstabelecimentoController(EstabelecimentoService estabelecimentoService) {
        this.estabelecimentoService = estabelecimentoService;
    }

    @GetMapping
    public List<Estabelecimento> getAll() {
        return estabelecimentoService.getAll();
    }

    @PostMapping
    public Estabelecimento create(@RequestBody Estabelecimento estabelecimento) {
        return estabelecimentoService.save(estabelecimento);
    }

    @PutMapping("/{id}")
    public Estabelecimento update(@PathVariable Long id, @RequestBody Estabelecimento estabelecimento, @RequestParam Long proprietarioLogadoId) {

        return estabelecimentoService.update(id, estabelecimento, proprietarioLogadoId);
    }
}
