package com.example.biblioteca.controllers;

import com.example.biblioteca.dtos.ClienteRequestDto;
import com.example.biblioteca.dtos.ClienteResponseDto;
import jakarta.persistence.EntityNotFoundException;
import com.example.biblioteca.model.Cliente;
import com.example.biblioteca.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDto>> findAll(@RequestParam(name = "numeroPagina", required = false, defaultValue = "0") int numeroPagina,
                                                            @RequestParam(name = "quantidade", required = false, defaultValue = "5") int quantidade) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        return ResponseEntity.ok(clienteRepository.findAll(pageRequest).map(cliente -> ClienteResponseDto.toDto(cliente)));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable("id") Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(ClienteResponseDto.toDto(clienteOpt.get()));
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDto> save(@RequestBody ClienteRequestDto dto) {
        Cliente cliente = dto.toCliente(new Cliente());
        clienteRepository.save(cliente);
        return ResponseEntity.created(URI.create("/clientes/" + cliente.getId())).body(ClienteResponseDto.toDto(cliente));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDto> update(@PathVariable("id") Integer id, @RequestBody ClienteRequestDto dto) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) {
            Cliente clienteSalvo = dto.toCliente(clienteOpt.get());
            return ResponseEntity.ok(ClienteResponseDto.toDto(clienteRepository.save(clienteSalvo)));
        } else {
            throw new EntityNotFoundException("Cliente não encontrado.");
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        clienteRepository.deleteById(id);
    }
}