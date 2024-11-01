package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.ExemplarRequestDto;
import com.example.biblioteca.dtos.ExemplarResponseDto;
import jakarta.persistence.EntityNotFoundException;
import com.example.biblioteca.model.Exemplar;
import com.example.biblioteca.repositories.ExemplarRepository;
import com.example.biblioteca.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("exemplares")
public class ExemplarController {

    private final ExemplarRepository exemplarRepository;
    private final LivroRepository livroRepository;

    public ExemplarController(ExemplarRepository exemplarRepository,
                              LivroRepository livroRepository) {
        this.exemplarRepository = exemplarRepository;
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public ResponseEntity<Page<ExemplarResponseDto>> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                             @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(exemplarRepository.findAll(pageRequest).map(exemplar -> ExemplarResponseDto.toDto(exemplar)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ExemplarResponseDto> findById(@PathVariable("id") Integer id) {
        Optional<Exemplar> exemplarOpt = exemplarRepository.findById(id);
        if (exemplarOpt.isPresent()) {
            return ResponseEntity.ok(ExemplarResponseDto.toDto(exemplarOpt.get()));
        } else {
            throw new EntityNotFoundException("Exemplar não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<ExemplarResponseDto> save(@RequestBody ExemplarRequestDto dto) {
        Exemplar exemplar = new Exemplar();
        exemplar = dto.toExemplar(exemplar, livroRepository);
        exemplarRepository.save(exemplar);
        return ResponseEntity.created(URI.create("/exemplares/" + exemplar.getId())).body(ExemplarResponseDto.toDto(exemplar));
    }

    @PutMapping("{id}")
    public ResponseEntity<ExemplarResponseDto> update(@PathVariable("id") Integer id, @RequestBody ExemplarRequestDto dto) {
        Optional<Exemplar> exemplarOpt = exemplarRepository.findById(id);
        if (exemplarOpt.isPresent()) {
            Exemplar exemplarSalvo = dto.toExemplar(exemplarOpt.get(), livroRepository);
            return ResponseEntity.ok(ExemplarResponseDto.toDto(exemplarRepository.save(exemplarSalvo)));
        } else {
            throw new EntityNotFoundException("Exemplar não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        exemplarRepository.deleteById(id);
    }
}
