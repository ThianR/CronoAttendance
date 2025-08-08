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

import com.cronoattendance.entities.HorariosEmpleados;
import com.cronoattendance.services.HorariosEmpleadosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/horarios-empleados")
@RequiredArgsConstructor
public class HorariosEmpleadosController {
    private final HorariosEmpleadosService servicio;

    @GetMapping
    public List<HorariosEmpleados> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorariosEmpleados> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public HorariosEmpleados crear(@RequestBody HorariosEmpleados he) {
        return servicio.save(he);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorariosEmpleados> actualizar(@PathVariable Integer id,
            @RequestBody HorariosEmpleados he) {
        he.setId(id);
        return ResponseEntity.ok(servicio.save(he));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
