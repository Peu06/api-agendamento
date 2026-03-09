package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
}
