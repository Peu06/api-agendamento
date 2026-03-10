package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long > {
    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    Cliente findByEmail(String email);
}
