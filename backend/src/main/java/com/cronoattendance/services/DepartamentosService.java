package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Departamentos;
import com.cronoattendance.repositories.DepartamentosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartamentosService {
    private final DepartamentosRepository repo;

    public List<Departamentos> findAll() {
        return repo.findAll();
    }

    public Departamentos findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Departamentos save(Departamentos d) {
        return repo.save(d);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
