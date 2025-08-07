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

import com.cronoattendance.entities.Empresas;
import com.cronoattendance.services.EmpresasService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresasController {
    private final EmpresasService EmpresasService;

    @GetMapping
    public List<Empresas> listar() {
        return EmpresasService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresas> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(EmpresasService.findById(id));
    }

    @PostMapping
    public Empresas crear(@RequestBody Empresas e) {
        return EmpresasService.save(e);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresas> actualizar(@PathVariable Integer id,
            @RequestBody Empresas e) {
        e.setId(id);
        return ResponseEntity.ok(EmpresasService.save(e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Integer id) {
        EmpresasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
