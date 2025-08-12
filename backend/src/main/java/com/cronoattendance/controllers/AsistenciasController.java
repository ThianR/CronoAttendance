// AsistenciasController.java
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

import com.cronoattendance.entities.Asistencias;
import com.cronoattendance.services.AsistenciasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciasController {
    private final AsistenciasService servicio;

    @GetMapping
    public List<Asistencias> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencias> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public Asistencias crear(@RequestBody Asistencias a) {
        return servicio.save(a);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistencias> actualizar(@PathVariable Integer id,
            @RequestBody Asistencias a) {
        a.setId(id);
        return ResponseEntity.ok(servicio.save(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
