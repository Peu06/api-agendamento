package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByFuncionarioIdAndInicio(Long funcionarioId, LocalDateTime inicio);
}
