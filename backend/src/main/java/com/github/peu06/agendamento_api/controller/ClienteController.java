package com.github.peu06.agendamento_api.controller;

import com.github.peu06.agendamento_api.model.Cliente;
import com.github.peu06.agendamento_api.repository.ClienteRepository;
import com.github.peu06.agendamento_api.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    // POST /cliente com validação
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Cliente cliente) {

        if (clienteService.existsByEmail(cliente.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email já está em uso"));
        }

        if (clienteService.existsByCpf(cliente.getCpf())) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "CPF já está em uso"));
        }

        Cliente novo = clienteService.save(cliente);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.update(id, cliente);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String senha = loginData.get("senha");

        if (email == null || senha == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Email e senha são obrigatórios"));
        }

        Cliente cliente = clienteService.autenticar(email, senha); // método de autenticação
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Email ou senha inválidos"));
        }

        // Retorna dados do usuário ou token JWT
        return ResponseEntity.ok(Map.of(
                "id", cliente.getId(),
                "nome", cliente.getNome(),
                "email", cliente.getEmail()
        ));
    }
}
