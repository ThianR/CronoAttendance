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

import com.cronoattendance.entities.Horarios;
import com.cronoattendance.services.HorariosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/horarios")
@RequiredArgsConstructor
public class HorariosController {
    private final HorariosService servicio;

    @GetMapping
    public List<Horarios> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horarios> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public Horarios crear(@RequestBody Horarios h) {
        return servicio.save(h);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Horarios> actualizar(@PathVariable Integer id,
            @RequestBody Horarios h) {
        h.setId(id);
        return ResponseEntity.ok(servicio.save(h));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
