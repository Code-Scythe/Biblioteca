package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Exemplar;

public record ExemplarResponseDto(Integer id,
                                  LivroResponseDto livro,
                                  int referencia,
                                  boolean disponivel) {

    public static ExemplarResponseDto toDto(Exemplar exemplar) {
        return new ExemplarResponseDto(
                exemplar.getId(),
                LivroResponseDto.toDto(exemplar.getLivro()),
                exemplar.getReferencia(),
                exemplar.getDisponivel()
        );
    }
}