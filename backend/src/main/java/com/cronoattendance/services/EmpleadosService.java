package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Empleados;
import com.cronoattendance.repositories.EmpleadosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpleadosService {
    private final EmpleadosRepository repo;

    public List<Empleados> findAll() {
        return repo.findAll();
    }

    public Empleados findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Empleados save(Empleados e) {
        return repo.save(e);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
