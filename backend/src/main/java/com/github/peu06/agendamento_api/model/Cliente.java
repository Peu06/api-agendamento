package com.github.peu06.agendamento_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false, length = 255)
    private String senha;

    @Past
    private LocalDate dtNascimento;

    @NotBlank
    @Size(max = 15)
    @Column(nullable = false, length = 15)
    private String telefone;

    @NotBlank
    @Size(max = 11)
    @Column(nullable = false, length = 11, unique = true)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String senha, LocalDate dtNascimento, String telefone, String cpf, List<Agendamento> agendamentos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dtNascimento = dtNascimento;
        this.telefone = telefone;
        this.cpf = cpf;
        this.agendamentos = agendamentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }
}
