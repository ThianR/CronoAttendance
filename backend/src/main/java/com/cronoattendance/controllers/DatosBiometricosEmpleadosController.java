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

import com.cronoattendance.entities.DatosBiometricosEmpleados;
import com.cronoattendance.services.DatosBiometricosEmpleadosService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/datos-biometricos-empleados")
@RequiredArgsConstructor
public class DatosBiometricosEmpleadosController {
    private final DatosBiometricosEmpleadosService servicio;

    @GetMapping
    public List<DatosBiometricosEmpleados> listar() {
        return servicio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosBiometricosEmpleados> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(servicio.findById(id));
    }

    @PostMapping
    public DatosBiometricosEmpleados crear(@RequestBody DatosBiometricosEmpleados d) {
        return servicio.save(d);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosBiometricosEmpleados> actualizar(@PathVariable Integer id,
            @RequestBody DatosBiometricosEmpleados d) {
        d.setId(id);
        return ResponseEntity.ok(servicio.save(d));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
