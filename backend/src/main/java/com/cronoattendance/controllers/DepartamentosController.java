// DepartamentosController.java
package com.cronoattendance.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronoattendance.entities.Departamentos;
import com.cronoattendance.services.DepartamentosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/departamentos")
@RequiredArgsConstructor
public class DepartamentosController {
    private final DepartamentosService servicio;

    @GetMapping
    public List<Departamentos> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamentos> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public Departamentos crear(@RequestBody Departamentos d) {
        return servicio.save(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamentos> actualizar(@PathVariable Integer id,
            @RequestBody Departamentos d) {
        d.setId(id);
        return ResponseEntity.ok(servicio.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
