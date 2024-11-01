package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.repositories.ExemplarRepository;
import com.example.biblioteca.repositories.ClienteRepository;

import java.time.LocalDate;

public record EmprestimoRequestDto(Integer idExemplar,
                                   Integer idCliente,
                                   LocalDate data) {

    public Emprestimo toEmprestimo(Emprestimo emprestimo,
                                   ExemplarRepository exemplarRepository,
                                   ClienteRepository clienteRepository) {
        emprestimo.setExemplar(exemplarRepository.getReferenceById(this.idExemplar()));
        emprestimo.setCliente(clienteRepository.getReferenceById(this.idCliente()));
        emprestimo.setData(this.data());

        return emprestimo;
    }
}