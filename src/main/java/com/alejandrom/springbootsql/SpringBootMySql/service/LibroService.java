package com.alejandrom.springbootsql.SpringBootMySql.service;

import com.alejandrom.springbootsql.SpringBootMySql.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> getAllLibros();

    Optional<Libro> getLibroById(Long id);

    Libro addLibro(Libro libro);

    Libro updateLibroById(Long id, Libro libroDetails);

    void deleteLibroById(Long id);
}