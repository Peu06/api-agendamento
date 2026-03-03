package com.github.peu06.agendamento_api.repository;

import com.github.peu06.agendamento_api.model.Estabelecimento;
import org.apache.el.parser.JJTELParserState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository extends JpaRepository<Estabelecimento, Long > {


}
