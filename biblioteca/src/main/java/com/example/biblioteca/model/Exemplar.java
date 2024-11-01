package com.example.biblioteca.model;

import com.example.biblioteca.model.Livro;
import jakarta.persistence.*;

@Entity
@Table(name = "exemplares")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @Column(name = "referencia", nullable = false)
    private int referencia;

    @Column(name = "disponivel", nullable = false)
    private boolean disponivel;

    public Exemplar() {
    }

    public Exemplar(Integer id, Livro livro, int referencia, boolean disponivel) {
        this.id = id;
        this.livro = livro;
        this.referencia = referencia;
        this.disponivel = disponivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getReferencia() {
        return referencia;
    }

    public void setReferencia(int referencia) {
        this.referencia = referencia;
    }

    // Para facilitar o entendimento, a função 'is' foi renomeada para 'get'.
    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}