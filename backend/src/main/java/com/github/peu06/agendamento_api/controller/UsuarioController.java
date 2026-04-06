package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Usuario;
import com.github.peu06.agendamento_api.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getAll(){
        return usuarioService.getAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Long id){
        return usuarioService.getById(id);
    }

    @PostMapping
    public Usuario post(@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }
}
