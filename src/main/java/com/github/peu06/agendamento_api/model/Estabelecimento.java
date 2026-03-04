package com.github.peu06.agendamento_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String produto;

    @Size(max = 15)
    @Column(length = 15)
    private String telefone;

    @Size(max = 150)
    @Column(length = 150, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String endereco;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "proprietario_id", nullable = false)
    private Proprietario proprietario;

    @JsonIgnore
    @OneToMany(mappedBy = "estabelecimento")
    private List<Funcionario> funcionarios;

    public Estabelecimento(){
    }

    public Estabelecimento(Long id, String nome, String produto, String telefone, String email, String endereco, Proprietario proprietario) {
        this.id = id;
        this.nome = nome;
        this.produto = produto;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.proprietario = proprietario;
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

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
