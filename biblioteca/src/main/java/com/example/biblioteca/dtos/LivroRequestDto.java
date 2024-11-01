package com.example.biblioteca.dtos;

import com.example.biblioteca.enums.Categoria;
import com.example.biblioteca.model.Livro;

public record LivroRequestDto(String nome,
                              String autor,
                              int anoPublicacao,
                              String isbn,
                              Categoria categoria) {

    public Livro toLivro(Livro livro) {
        livro.setNome(this.nome());
        livro.setAutor(this.autor());
        livro.setAnoPublicacao(this.anoPublicacao());
        livro.setIsbn(this.isbn());
        livro.setCategoria(this.categoria());

        return livro;
    }
}