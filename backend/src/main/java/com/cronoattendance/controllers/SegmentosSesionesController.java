// SegmentosSesionesController.java
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

import com.cronoattendance.entities.SegmentosSesiones;
import com.cronoattendance.services.SegmentosSesionesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/segmentos-sesiones")
@RequiredArgsConstructor
public class SegmentosSesionesController {
    private final SegmentosSesionesService servicio;

    @GetMapping
    public List<SegmentosSesiones> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentosSesiones> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public SegmentosSesiones crear(@RequestBody SegmentosSesiones seg) {
        return servicio.save(seg);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SegmentosSesiones> actualizar(@PathVariable Integer id,
            @RequestBody SegmentosSesiones seg) {
        seg.setId(id);
        return ResponseEntity.ok(servicio.save(seg));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
