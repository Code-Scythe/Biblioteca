package com.example.biblioteca.dtos;

import com.example.biblioteca.model.Cliente;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Exemplar;

import java.time.LocalDate;

public record EmprestimoResponseDto(Integer id,
                                    ExemplarResponseDto exemplar,
                                    ClienteResponseDto cliente,
                                    LocalDate data) {

    public static EmprestimoResponseDto toDto(Emprestimo emprestimo) {
        return new EmprestimoResponseDto(
                emprestimo.getId(),
                ExemplarResponseDto.toDto(emprestimo.getExemplar()),
                ClienteResponseDto.toDto(emprestimo.getCliente()),
                emprestimo.getData()
        );
    }
}