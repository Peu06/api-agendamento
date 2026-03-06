package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Funcionario;
import com.github.peu06.agendamento_api.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAll() {return funcionarioRepository.findAll();}

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario funcionarioAtualizado, Long estabelecimentoLogadoId) {

        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException(
                    "Funcionario não encontrado com id: " + id));

        if (!funcionarioExistente.getEstabelecimento().getId().equals(estabelecimentoLogadoId)){
            throw new RuntimeException("Você não tem permissão para esse funcionario");
        }

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
        funcionarioExistente.setSenha(funcionarioAtualizado.getSenha());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setDtNascimento(funcionarioAtualizado.getDtNascimento());

        return funcionarioRepository.save(funcionarioExistente);
    }

    public void delete(Long id){funcionarioRepository.deleteById(id);}
}
