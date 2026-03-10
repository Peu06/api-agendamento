package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Estabelecimento;
import com.github.peu06.agendamento_api.repository.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Estabelecimento update(Long id, Estabelecimento estabelecimentoAtualizado, Long proprietarioLogadoId) {

        Estabelecimento estabelecimentoExistente = estabelecimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Estabelecimento não encontrado com id: " + id));

        if (!estabelecimentoExistente.getProprietario().getId().equals(proprietarioLogadoId)) {
            throw new RuntimeException("Você não tem permissão para editar este estabelecimento");
        }

        estabelecimentoExistente.setNome(estabelecimentoAtualizado.getNome());
        estabelecimentoExistente.setProduto(estabelecimentoAtualizado.getProduto());
        estabelecimentoExistente.setTelefone(estabelecimentoAtualizado.getTelefone());
        estabelecimentoExistente.setEmail(estabelecimentoAtualizado.getEmail());
        estabelecimentoExistente.setEndereco(estabelecimentoAtualizado.getEndereco());

        return estabelecimentoRepository.save(estabelecimentoExistente);
    }

    public Optional<Estabelecimento> getById(Long id) {
        return estabelecimentoRepository.findById(id);
    }

    public void delete(Long id){estabelecimentoRepository.deleteById(id);}

}
