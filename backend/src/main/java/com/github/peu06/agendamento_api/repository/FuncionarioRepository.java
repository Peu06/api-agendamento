package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
