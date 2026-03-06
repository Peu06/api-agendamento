package com.github.peu06.agendamento_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDateTime inicio;

    @NotNull
    @FutureOrPresent
    @Column(nullable = false)
    private LocalDateTime fim;

    @Column(nullable = false)
    private boolean status;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @JsonIgnore
    @OneToMany(mappedBy = "agenda")
    private List<Agendamento> agendamentos;

    public Agenda() {
    }

    public Agenda(Long id, LocalDateTime inicio, LocalDateTime fim, boolean status, Funcionario funcionario, List<Agendamento> agendamentos) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.status = status;
        this.funcionario = funcionario;
        this.agendamentos = agendamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
