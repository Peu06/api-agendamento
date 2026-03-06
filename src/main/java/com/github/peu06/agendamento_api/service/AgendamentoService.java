package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Agenda;
import com.github.peu06.agendamento_api.model.Agendamento;
import com.github.peu06.agendamento_api.repository.AgendaRepository;
import com.github.peu06.agendamento_api.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final AgendaRepository agendaRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              AgendaRepository agendaRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendaRepository = agendaRepository;
    }

    public List<Agendamento> getAll() {
        return agendamentoRepository.findAll();
    }

    public Agendamento getById(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado com id: " + id));
    }

    public Agendamento save(Agendamento agendamento) {

        Agenda agenda = agendaRepository.findById(agendamento.getAgenda().getId())
                .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

        if (!agenda.getStatus()) {
            throw new RuntimeException("Este horário já está ocupado");
        }

        agenda.setStatus(false);
        agendaRepository.save(agenda);

        agendamento.setAgenda(agenda);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento update(Long id, Agendamento agendamentoAtualizado, Long clienteLogadoId) {

        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        if (!agendamentoExistente.getCliente().getId().equals(clienteLogadoId)) {
            throw new RuntimeException("Você não tem permissão para editar esse agendamento.");
        }

        Long agendaAtualId = agendamentoExistente.getAgenda().getId();
        Long novaAgendaId = agendamentoAtualizado.getAgenda().getId();

        if (!agendaAtualId.equals(novaAgendaId)) {

            Agenda novaAgenda = agendaRepository.findById(novaAgendaId)
                    .orElseThrow(() -> new RuntimeException("Agenda não encontrada"));

            if (!novaAgenda.getStatus()) {
                throw new RuntimeException("Este horário já está ocupado");
            }

            Agenda agendaAntiga = agendamentoExistente.getAgenda();
            agendaAntiga.setStatus(true);
            agendaRepository.save(agendaAntiga);

            novaAgenda.setStatus(false);
            agendaRepository.save(novaAgenda);

            agendamentoExistente.setAgenda(novaAgenda);
        }

        agendamentoExistente.setStatus(agendamentoAtualizado.getStatus());
        agendamentoExistente.setFuncionario(agendamentoAtualizado.getFuncionario());

        return agendamentoRepository.save(agendamentoExistente);
    }

    public void delete(Long id) {

        Agendamento agendamento = getById(id);

        Agenda agenda = agendamento.getAgenda();
        agenda.setStatus(true);

        agendaRepository.save(agenda);

        agendamentoRepository.delete(agendamento);
    }
}