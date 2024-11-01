package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Cliente;

public record ClienteResponseDto(Integer id,
                                 String nome,
                                 String email,
                                 boolean apto) {

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getApto()
        );
    }
}