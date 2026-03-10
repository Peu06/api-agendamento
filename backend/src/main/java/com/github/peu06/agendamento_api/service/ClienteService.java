package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Cliente;
import com.github.peu06.agendamento_api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id) .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setSenha(clienteAtualizado.getSenha());
        clienteExistente.setDtNascimento(clienteAtualizado.getDtNascimento());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setCpf(clienteAtualizado.getCpf());

        return clienteRepository.save(clienteExistente);
    }

    public boolean existsByEmail(String email) {
        return clienteRepository.existsByEmail(email);
    }

    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    public Cliente autenticar(String email, String senha) {

        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            return null;
        }

        if (cliente.getSenha().equals(senha)) {
            return cliente;
        }

        return null;
}
}
