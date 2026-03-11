package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.dto.LoginRequest;
import com.github.peu06.agendamento_api.model.Cliente;
import com.github.peu06.agendamento_api.model.Funcionario;
import com.github.peu06.agendamento_api.model.Proprietario;
import com.github.peu06.agendamento_api.repository.ClienteRepository;
import com.github.peu06.agendamento_api.repository.FuncionarioRepository;
import com.github.peu06.agendamento_api.repository.ProprietarioRepository;
import com.github.peu06.agendamento_api.util.PasswordUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final ClienteRepository clienteRepository;
    private final ProprietarioRepository proprietarioRepository;
    private final FuncionarioRepository funcionarioRepository;

    public AuthController(ClienteRepository clienteRepository, ProprietarioRepository proprietarioRepository, FuncionarioRepository funcionarioRepository) {
        this.clienteRepository = clienteRepository;
        this.proprietarioRepository = proprietarioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping
    public Map<String, String> login(@RequestBody LoginRequest login, HttpSession session) {

        Cliente cliente = clienteRepository.findByEmail(login.getEmail());

        if (cliente != null && PasswordUtil.verificarSenha(login.getSenha(), cliente.getSenha())){
            session.setAttribute("usuario", cliente);
            session.setAttribute("tipo", "CLIENTE");
            return Map.of("tipo", "CLIENTE");
        }

        Funcionario funcionario = funcionarioRepository
                .findByEmail(login.getEmail())
                .orElse(null);

        if (funcionario != null && PasswordUtil.verificarSenha(login.getSenha(), funcionario.getSenha())){
            session.setAttribute("usuario", funcionario);
            session.setAttribute("tipo", "FUNCIONARIO");
            return Map.of("tipo", "FUNCIONARIO");
        }

        Proprietario proprietario = (Proprietario) proprietarioRepository
                .findByEmail(login.getEmail())
                .orElse(null);

        if (proprietario != null && PasswordUtil.verificarSenha(login.getSenha(), proprietario.getSenha())){
            session.setAttribute("usuario", proprietario);
            session.setAttribute("tipo", "PROPRIETARIO");
            return Map.of("tipo", "PROPRIETARIO");
        }

        throw new RuntimeException("Email ou senha inválido");

    }
}
