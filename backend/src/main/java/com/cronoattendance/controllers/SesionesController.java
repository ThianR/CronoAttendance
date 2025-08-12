// SesionesController.java
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

import com.cronoattendance.entities.Sesiones;
import com.cronoattendance.services.SesionesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sesiones")
@RequiredArgsConstructor
public class SesionesController {
    private final SesionesService servicio;

    @GetMapping
    public List<Sesiones> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sesiones> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public Sesiones crear(@RequestBody Sesiones s) {
        return servicio.save(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sesiones> actualizar(@PathVariable Integer id,
            @RequestBody Sesiones s) {
        s.setId(id);
        return ResponseEntity.ok(servicio.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
