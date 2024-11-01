package com.example.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    // ###.###.###-##
    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    // +## (##) #####-####
    @Column(name = "telefone", nullable = false, length = 19)
    private String telefone;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "apto", nullable = false)
    private boolean apto;

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String telefone, String email, boolean apto) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.apto = apto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    // Para facilitar o entendimento, a função 'is' foi renomeada para 'get'.
    public boolean getApto() {
        return apto;
    }

    public void setApto(boolean apto) {
        this.apto = apto;
    }
}