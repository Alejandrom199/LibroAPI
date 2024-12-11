package com.alejandrom.springbootsql.SpringBootMySql.controller;

import com.alejandrom.springbootsql.SpringBootMySql.model.Libro;
import com.alejandrom.springbootsql.SpringBootMySql.service.LibroServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private LibroServiceImp libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> getAllLibros() {
        return ResponseEntity.ok(libroService.getAllLibros());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        return libroService.getLibroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro) {
        try{
            Libro addedLibro = libroService.addLibro(libro);
            return ResponseEntity.ok(addedLibro);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        try{
            Libro updatedLibro = libroService.updateLibroById(id, libroDetails);
            return ResponseEntity.ok(updatedLibro);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        try{
            libroService.deleteLibroById(id);
            return ResponseEntity.noContent().build();
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }
}
