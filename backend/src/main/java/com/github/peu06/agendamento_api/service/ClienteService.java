package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Cliente;
import com.github.peu06.agendamento_api.repository.ClienteRepository;
import com.github.peu06.agendamento_api.util.PasswordUtil;
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

    public Cliente save(Cliente cliente){

        if(clienteRepository.existsByEmail(cliente.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(clienteRepository.existsByCpf(cliente.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }

        String senhaHash = PasswordUtil.hashSenha(cliente.getSenha());
        cliente.setSenha(senhaHash);

        return clienteRepository.save(cliente);
    }

    public Cliente update(Long id, Cliente clienteAtualizado) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setDtNascimento(clienteAtualizado.getDtNascimento());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setCpf(clienteAtualizado.getCpf());

        if(clienteAtualizado.getSenha() != null && !clienteAtualizado.getSenha().isEmpty()){
            String senhaHash = PasswordUtil.hashSenha(clienteAtualizado.getSenha());
            clienteExistente.setSenha(senhaHash);
        }

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

        if(cliente == null){
            return null;
        }

        boolean senhaValida = PasswordUtil.verificarSenha(senha, cliente.getSenha());

        if(senhaValida){
            return cliente;
        }

        return null;
    }
}