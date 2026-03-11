package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Funcionario;
import com.github.peu06.agendamento_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);
}
