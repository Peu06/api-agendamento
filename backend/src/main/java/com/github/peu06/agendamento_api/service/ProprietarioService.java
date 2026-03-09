package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Proprietario;
import com.github.peu06.agendamento_api.repository.ProprietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public ProprietarioService(ProprietarioRepository proprietarioRepository) {
        this.proprietarioRepository = proprietarioRepository;
    }

    public List<Proprietario> getAll(){ return proprietarioRepository.findAll();}

    public Proprietario save(Proprietario proprietario) {

        return proprietarioRepository.save(proprietario);
    }

    public Proprietario update(Long id, Proprietario proprietarioAtualizado) {
        Proprietario proprietarioExistente = proprietarioRepository.findById(id) .orElseThrow(() -> new RuntimeException("Proprietario não encontrado com id: " + id));

        proprietarioExistente.setNome(proprietarioAtualizado.getNome());
        proprietarioExistente.setEmail(proprietarioAtualizado.getEmail());
        proprietarioExistente.setSenha(proprietarioAtualizado.getSenha());
        proprietarioExistente.setDtNascimento(proprietarioAtualizado.getDtNascimento());
        proprietarioExistente.setTelefone(proprietarioAtualizado.getTelefone());

        return proprietarioRepository.save(proprietarioExistente);
    }
}