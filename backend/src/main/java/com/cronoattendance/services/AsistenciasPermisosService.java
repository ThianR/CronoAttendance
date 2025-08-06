package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.AsistenciasPermisos;
import com.cronoattendance.repositories.AsistenciasPermisosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciasPermisosService {
    private final AsistenciasPermisosRepository repo;

    public List<AsistenciasPermisos> findAll() {
        return repo.findAll();
    }

    public AsistenciasPermisos findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public AsistenciasPermisos save(AsistenciasPermisos p) {
        return repo.save(p);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
