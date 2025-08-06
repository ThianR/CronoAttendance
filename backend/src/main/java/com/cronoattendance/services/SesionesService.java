package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Sesiones;
import com.cronoattendance.repositories.SesionesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SesionesService {
    private final SesionesRepository repo;

    public List<Sesiones> findAll() {
        return repo.findAll();
    }

    public Sesiones findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Sesiones save(Sesiones s) {
        return repo.save(s);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
