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

import com.cronoattendance.entities.SegmentosHorarios;
import com.cronoattendance.services.SegmentosHorariosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/segmentos-horarios")
@RequiredArgsConstructor
public class SegmentosHorariosController {
    private final SegmentosHorariosService servicio;

    @GetMapping
    public List<SegmentosHorarios> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentosHorarios> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public SegmentosHorarios crear(@RequestBody SegmentosHorarios s) {
        return servicio.save(s);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SegmentosHorarios> actualizar(@PathVariable Integer id,
            @RequestBody SegmentosHorarios s) {
        s.setId(id);
        return ResponseEntity.ok(servicio.save(s));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
