// src/main/java/com/cronoattendance/services/HorarioEmpleadoService.java
package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.HorariosEmpleados;
import com.cronoattendance.repositories.HorariosEmpleadosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HorariosEmpleadosService {
    private final HorariosEmpleadosRepository repo;

    public List<HorariosEmpleados> findAll() {
        return repo.findAll();
    }

    public HorariosEmpleados findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public HorariosEmpleados save(HorariosEmpleados he) {
        return repo.save(he);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
