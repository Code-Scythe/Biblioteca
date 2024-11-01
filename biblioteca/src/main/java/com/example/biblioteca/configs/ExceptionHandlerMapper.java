package com.example.biblioteca.configs;

import com.example.biblioteca.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionHandlerMapper {

    @ExceptionHandler(ClienteNaoAptoException.class)
    public ResponseEntity<ProblemDetail> handle(ClienteNaoAptoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(403);
        problemDetail.setTitle("Erro: Cliente inapto para realizar empréstimo");
        problemDetail.setDetail(ex.getMessage());

        problemDetail.setProperty("dataHoraErro", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));
        problemDetail.setProperty("numeroRegraNegocio", ex.getNumeroRegraNegocio());

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(ExemplarNaoDisponivelException.class)
    public ResponseEntity<ProblemDetail> handle(ExemplarNaoDisponivelException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(409);
        problemDetail.setTitle("Erro: Exemplar indisponível para empréstimo");
        problemDetail.setDetail(ex.getMessage());

        problemDetail.setProperty("dataHoraErro", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));
        problemDetail.setProperty("numeroRegraNegocio", ex.getNumeroRegraNegocio());

        return ResponseEntity.of(problemDetail).build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handle(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(404);
        problemDetail.setTitle("Erro: Recurso não encontrado");
        problemDetail.setDetail(ex.getMessage());

        problemDetail.setProperty("dataHoraErro", LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm")));

        return ResponseEntity.of(problemDetail).build();
    }
}