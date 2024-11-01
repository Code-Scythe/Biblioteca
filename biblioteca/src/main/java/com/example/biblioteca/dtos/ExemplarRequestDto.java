package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Exemplar;
import com.example.biblioteca.repositories.LivroRepository;

public record ExemplarRequestDto(Integer idLivro,
                                 int referencia,
                                 boolean disponivel) {

    public Exemplar toExemplar(Exemplar exemplar, LivroRepository livroRepository) {
        exemplar.setLivro(livroRepository.getReferenceById(this.idLivro()));
        exemplar.setReferencia(this.referencia());
        exemplar.setDisponivel(this.disponivel());

        return exemplar;
    }
}