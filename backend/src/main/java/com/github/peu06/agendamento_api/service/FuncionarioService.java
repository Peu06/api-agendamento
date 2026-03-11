package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Funcionario;
import com.github.peu06.agendamento_api.repository.FuncionarioRepository;
import com.github.peu06.agendamento_api.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario save(Funcionario funcionario) {

        if(funcionarioRepository.existsByEmail(funcionario.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(funcionarioRepository.existsByCpf(funcionario.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }

        String senhaHash = PasswordUtil.hashSenha(funcionario.getSenha());
        funcionario.setSenha(senhaHash);

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario update(Long id, Funcionario funcionarioAtualizado, Long estabelecimentoLogadoId) {

        Funcionario funcionarioExistente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Funcionário não encontrado com id: " + id));

        if(!funcionarioExistente.getEstabelecimento().getId().equals(estabelecimentoLogadoId)){
            throw new RuntimeException("Você não tem permissão para esse funcionário");
        }

        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
        funcionarioExistente.setTelefone(funcionarioAtualizado.getTelefone());
        funcionarioExistente.setCpf(funcionarioAtualizado.getCpf());
        funcionarioExistente.setDtNascimento(funcionarioAtualizado.getDtNascimento());

        if(funcionarioAtualizado.getSenha() != null && !funcionarioAtualizado.getSenha().isEmpty()){
            String senhaHash = PasswordUtil.hashSenha(funcionarioAtualizado.getSenha());
            funcionarioExistente.setSenha(senhaHash);
        }

        return funcionarioRepository.save(funcionarioExistente);
    }

    public void delete(Long id, Long estabelecimentoLogadoId){

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        if(!funcionario.getEstabelecimento().getId().equals(estabelecimentoLogadoId)){
            throw new RuntimeException("Você não tem permissão para excluir esse funcionário");
        }

        funcionarioRepository.delete(funcionario);
    }
}