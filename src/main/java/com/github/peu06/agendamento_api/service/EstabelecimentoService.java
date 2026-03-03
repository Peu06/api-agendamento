package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Estabelecimento;
import com.github.peu06.agendamento_api.repository.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoService(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public List<Estabelecimento> getAll() {
        return estabelecimentoRepository.findAll();
    }

    public Estabelecimento save(Estabelecimento estabelecimento) {
        return estabelecimentoRepository.save(estabelecimento);
    }

    public Estabelecimento update(Long id, Estabelecimento estabelecimentoAtualizado) {

        Estabelecimento estabelecimentoExistente = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado com id: " + id));

        estabelecimentoExistente.setNome(estabelecimentoAtualizado.getNome());
        estabelecimentoExistente.setEmail(estabelecimentoAtualizado.getEmail());
        estabelecimentoExistente.setSenha(estabelecimentoAtualizado.getSenha());
        estabelecimentoExistente.setEndereco(estabelecimentoAtualizado.getEndereco());

        return estabelecimentoRepository.save(estabelecimentoExistente);
    }

    public void delete(Long id){estabelecimentoRepository.deleteById(id);}

}
