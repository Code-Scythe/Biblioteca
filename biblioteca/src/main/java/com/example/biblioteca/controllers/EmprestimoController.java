package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.EmprestimoRequestDto;
import com.example.biblioteca.dtos.EmprestimoResponseDto;
import com.example.biblioteca.exceptions.ExemplarNaoDisponivelException;
import com.example.biblioteca.model.Cliente;
import com.example.biblioteca.model.Emprestimo;
import com.example.biblioteca.model.Exemplar;
import com.example.biblioteca.repositories.EmprestimoRepository;
import com.example.biblioteca.repositories.ExemplarRepository;
import com.example.biblioteca.repositories.ClienteRepository;
import com.example.biblioteca.exceptions.ClienteNaoAptoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    private final ClienteRepository clienteRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository,
                                ExemplarRepository exemplarRepository,
                                ClienteRepository clienteRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<Page<EmprestimoResponseDto>> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                               @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(emprestimoRepository.findAll(pageRequest).map(EmprestimoResponseDto::toDto));
    }

    @GetMapping("{id}")
    public ResponseEntity<EmprestimoResponseDto> findById(@PathVariable("id") Integer id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);
        if (emprestimoOpt.isPresent()) {
            return ResponseEntity.ok(EmprestimoResponseDto.toDto(emprestimoOpt.get()));
        } else {
            throw new EntityNotFoundException("Empréstimo não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<EmprestimoResponseDto> save(@RequestBody EmprestimoRequestDto requestDto) {

        Optional<Exemplar> exemplarOpt = exemplarRepository.findById(requestDto.idExemplar());
        if (exemplarOpt.isEmpty()) {
            throw new EntityNotFoundException("Exemplar não encontrado.");
        }

        Exemplar exemplar = exemplarOpt.get();
        if (!exemplar.getDisponivel()) {
            throw new ExemplarNaoDisponivelException("O exemplar solicitado não está disponível no momento.", 1001);
        }

        Optional<Cliente> clienteOpt = clienteRepository.findById(requestDto.idCliente());
        if (clienteOpt.isEmpty()) {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }

        Cliente cliente = clienteOpt.get();
        if (!cliente.getApto()) {
            throw new ClienteNaoAptoException("Infelizmente, o cliente é inapto para solicitar um empréstimo.", 1002);
        }

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setExemplar(exemplar);
        emprestimo.setCliente(cliente);
        emprestimo.setData(requestDto.data());

        emprestimoRepository.save(emprestimo);

        exemplar.setDisponivel(false);
        exemplarRepository.save(exemplar);

        return ResponseEntity.created(URI.create("/emprestimos/" + emprestimo.getId())).body(EmprestimoResponseDto.toDto(emprestimo));
    }

    @PutMapping("{id}")
    public ResponseEntity<EmprestimoResponseDto> update(@PathVariable("id") Integer id, @RequestBody EmprestimoRequestDto dto) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);
        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimoSalvo = dto.toEmprestimo(emprestimoOpt.get(), exemplarRepository, clienteRepository);
            return ResponseEntity.ok(EmprestimoResponseDto.toDto(emprestimoRepository.save(emprestimoSalvo)));
        } else {
            throw new EntityNotFoundException("Empréstimo não encontrado.");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);

        if (emprestimoOpt.isPresent()) {
            Emprestimo emprestimo = emprestimoOpt.get();
            Exemplar exemplar = emprestimo.getExemplar();
            exemplar.setDisponivel(true);
            exemplarRepository.save(exemplar);

            emprestimoRepository.delete(emprestimo);
        } else {
            throw new EntityNotFoundException("Empréstimo não encontrado.");
        }
    }
}
