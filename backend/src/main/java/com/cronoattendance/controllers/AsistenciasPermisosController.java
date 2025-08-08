// AsistenciasPermisosController.java
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

import com.cronoattendance.entities.AsistenciasPermisos;
import com.cronoattendance.services.AsistenciasPermisosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/asistencias-permisos")
@RequiredArgsConstructor
public class AsistenciasPermisosController {
    private final AsistenciasPermisosService servicio;

    @GetMapping
    public List<AsistenciasPermisos> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsistenciasPermisos> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public AsistenciasPermisos crear(@RequestBody AsistenciasPermisos p) {
        return servicio.save(p);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsistenciasPermisos> actualizar(@PathVariable Integer id,
            @RequestBody AsistenciasPermisos p) {
        p.setId(id);
        return ResponseEntity.ok(servicio.save(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
