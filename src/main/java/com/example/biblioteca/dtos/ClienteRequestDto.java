package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Cliente;

public record ClienteRequestDto(String nome,
                                String cpf,
                                String telefone,
                                String email,
                                boolean apto) {

    public Cliente toCliente(Cliente cliente) {
        cliente.setNome(this.nome());
        cliente.setCpf(this.cpf());
        cliente.setTelefone(this.telefone());
        cliente.setEmail(this.email());
        cliente.setApto(this.apto());

        return cliente;
    }
}