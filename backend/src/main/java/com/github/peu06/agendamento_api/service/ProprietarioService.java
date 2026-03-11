package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Proprietario;
import com.github.peu06.agendamento_api.repository.ProprietarioRepository;
import com.github.peu06.agendamento_api.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<Proprietario> getAll(){
        return proprietarioRepository.findAll();
    }

    public Proprietario save(Proprietario proprietario) {

        if(proprietarioRepository.existsByEmail(proprietario.getEmail())){
            throw new RuntimeException("Email já cadastrado");
        }

        if(proprietarioRepository.existsByCpf(proprietario.getCpf())){
            throw new RuntimeException("CPF já cadastrado");
        }

        String senhaHash = PasswordUtil.hashSenha(proprietario.getSenha());
        proprietario.setSenha(senhaHash);

        return proprietarioRepository.save(proprietario);
    }

    public Proprietario update(Long id, Proprietario proprietarioAtualizado) {

        Proprietario proprietarioExistente = proprietarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado com id: " + id));

        proprietarioExistente.setNome(proprietarioAtualizado.getNome());
        proprietarioExistente.setEmail(proprietarioAtualizado.getEmail());
        proprietarioExistente.setDtNascimento(proprietarioAtualizado.getDtNascimento());
        proprietarioExistente.setTelefone(proprietarioAtualizado.getTelefone());

        if(proprietarioAtualizado.getSenha() != null && !proprietarioAtualizado.getSenha().isEmpty()){
            String senhaHash = PasswordUtil.hashSenha(proprietarioAtualizado.getSenha());
            proprietarioExistente.setSenha(senhaHash);
        }

        return proprietarioRepository.save(proprietarioExistente);
    }
}