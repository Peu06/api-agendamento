package com.github.peu06.agendamento_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario{

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Agendamento> agendamentos;

    public Cliente() {
    }

    public List<Agendamento> getAgendamentos(){
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos){
        this.agendamentos = agendamentos;
    }
}
