package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Agenda;
import com.github.peu06.agendamento_api.model.Estabelecimento;
import com.github.peu06.agendamento_api.repository.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public List<Agenda> getAll() {
        return agendaRepository.findAll();
    }

    public Agenda save(Agenda agenda) {

        return agendaRepository.save(agenda);
    }

    public Agenda update(Long id, Agenda agendaAtualizado, Long funcionarioLogadoId) {

        Agenda agendaExistente = agendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Agenda não encontrado com id: " + id));

        if (!agendaExistente.getFuncionario().getId().equals(funcionarioLogadoId)) {
            throw new RuntimeException("Você não tem permissão para editar esta agenda");
        }

        agendaExistente.setInicio(agendaAtualizado.getInicio());
        agendaExistente.setFim(agendaAtualizado.getFim());
        agendaExistente.setStatus(agendaAtualizado.getStatus());

        return agendaRepository.save(agendaExistente);
    }



    public void delete(Long id){agendaRepository.deleteById(id);}
}