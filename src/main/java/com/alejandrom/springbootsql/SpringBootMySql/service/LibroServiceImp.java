package com.alejandrom.springbootsql.SpringBootMySql.service;

import com.alejandrom.springbootsql.SpringBootMySql.model.Libro;
import com.alejandrom.springbootsql.SpringBootMySql.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImp implements LibroService{

    @Autowired
    private LibroRepository libroRepository;

    public LibroServiceImp(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    public Optional<Libro> getLibroById(Long id) {
        return libroRepository.findById(id);
    }

    public Libro addLibro(Libro libro) {
        try {
            return libroRepository.save(libro);
        } catch (Exception e) {
            System.err.println("Error al guardar el libro: " + e.getMessage());
            return null;
        }
    }

    public Libro updateLibroById(Long id, Libro libroDetails) {
        return libroRepository.findById(id).map(libro -> {
            libro.setTitulo(libroDetails.getTitulo());
            libro.setAutor(libroDetails.getAutor());
            libro.setAnioPublicacion(libroDetails.getAnioPublicacion());
            return libroRepository.save(libro);
        }).orElse(null);
    }

    public void deleteLibroById(Long id) {
        libroRepository.deleteById(id);
    }
}
