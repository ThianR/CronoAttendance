package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.SegmentosSesiones;
import com.cronoattendance.repositories.SegmentosSesionesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SegmentosSesionesService {
    private final SegmentosSesionesRepository repo;

    public List<SegmentosSesiones> findAll() {
        return repo.findAll();
    }

    public SegmentosSesiones findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public SegmentosSesiones save(SegmentosSesiones seg) {
        return repo.save(seg);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
