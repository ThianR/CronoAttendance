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

import com.cronoattendance.entities.Empleados;
import com.cronoattendance.services.EmpleadosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class EmpleadosController {
    private final EmpleadosService servicio;

    @GetMapping
    public List<Empleados> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleados> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public Empleados crear(@RequestBody Empleados e) {
        return servicio.save(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleados> actualizar(@PathVariable Integer id,
            @RequestBody Empleados e) {
        e.setId(id);
        return ResponseEntity.ok(servicio.save(e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
