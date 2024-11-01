package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.ClienteResponseDto;
import com.example.biblioteca.dtos.LivroRequestDto;
import com.example.biblioteca.dtos.LivroResponseDto;
import jakarta.persistence.EntityNotFoundException;
import com.example.biblioteca.model.Livro;
import com.example.biblioteca.repositories.LivroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public ResponseEntity<Page<LivroResponseDto>> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                          @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(livroRepository.findAll(pageRequest).map(livro -> LivroResponseDto.toDto(livro)));
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroResponseDto> findById(@PathVariable("id") Integer id) {
        Optional<Livro> livroOpt = livroRepository.findById(id);
        if (livroOpt.isPresent()) {
            return ResponseEntity.ok(LivroResponseDto.toDto(livroOpt.get()));
        } else {
            throw new EntityNotFoundException("Livro não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<LivroResponseDto> save(@RequestBody LivroRequestDto dto) {
        Livro livro = dto.toLivro(new Livro());
        livroRepository.save(livro);
        return ResponseEntity.created(URI.create("/livros/" + livro.getId())).body(LivroResponseDto.toDto(livro));
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroResponseDto> update(@PathVariable("id") Integer id, @RequestBody LivroRequestDto dto) {
        Optional<Livro> livroOpt = livroRepository.findById(id);
        if (livroOpt.isPresent()) {
            Livro livroSalvo = dto.toLivro(livroOpt.get());
            return ResponseEntity.ok(LivroResponseDto.toDto(livroRepository.save(livroSalvo)));
        } else {
            throw new EntityNotFoundException("Livro não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        livroRepository.deleteById(id);
    }
}