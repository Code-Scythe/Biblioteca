package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.Categoria;
import com.example.biblioteca.model.Livro;

public record LivroResponseDto(Integer id,
                               String nome,
                               String autor,
                               Categoria categoria) {

    public static LivroResponseDto toDto(Livro livro) {
        return new LivroResponseDto(
                livro.getId(),
                livro.getNome(),
                livro.getAutor(),
                livro.getCategoria()
        );
    }
}