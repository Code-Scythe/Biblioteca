package com.example.biblioteca.repositories;

import com.example.biblioteca.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
}