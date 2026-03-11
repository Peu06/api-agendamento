package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Usuario;
import com.github.peu06.agendamento_api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com o ID: " + id));
    }

    public Usuario getByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado com email: " + email));
    }

    public void validarUsuario(Usuario usuario){

        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }
    }
}
