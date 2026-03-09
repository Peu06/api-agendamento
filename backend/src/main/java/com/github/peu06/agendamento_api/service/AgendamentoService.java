package com.github.peu06.agendamento_api.service;

import com.github.peu06.agendamento_api.model.Agenda;
import com.github.peu06.agendamento_api.model.Agendamento;
import com.github.peu06.agendamento_api.repository.AgendaRepository;
import com.github.peu06.agendamento_api.repository.AgendamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Agendamento não encontrado com id: " + id));
    }

    @Transactional
    public Agendamento save(Agendamento agendamento) {

        Agenda agenda = agendaRepository.findById(agendamento.getAgenda().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Agenda não encontrada"));

        if (agenda.getInicio().isBefore(LocalDateTime.now())){
            agenda.setStatus(Agenda.Status.HORARIO_PASSADO);
            agendaRepository.save(agenda);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível agendar horário passado");
        }

        if (agenda.getStatus() == Agenda.Status.AGENDADO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este horário já está ocupado");
        }

        agenda.setStatus(Agenda.Status.AGENDADO);
        agendaRepository.save(agenda);

        agendamento.setAgenda(agenda);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento update(Long id, Agendamento agendamentoAtualizado, Long clienteLogadoId) {

        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Agendamento não encontrado"));

        if (!agendamentoExistente.getCliente().getId().equals(clienteLogadoId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Você não tem permissão para editar esse agendamento.");
        }

        Long agendaAtualId = agendamentoExistente.getAgenda().getId();
        Long novaAgendaId = agendamentoAtualizado.getAgenda().getId();

        if (!agendaAtualId.equals(novaAgendaId)) {

            Agenda novaAgenda = agendaRepository.findById(novaAgendaId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Agenda não encontrada"));

            if (novaAgenda.getStatus() == Agenda.Status.AGENDADO) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Este horário já está ocupado");
            }

            Agenda agendaAntiga = agendamentoExistente.getAgenda();
            agendaAntiga.setStatus(Agenda.Status.DISPONIVEL);
            agendaRepository.save(agendaAntiga);

            novaAgenda.setStatus(Agenda.Status.AGENDADO);
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
        agenda.setStatus(Agenda.Status.DISPONIVEL);

        agendaRepository.save(agenda);

        agendamentoRepository.delete(agendamento);
    }
}