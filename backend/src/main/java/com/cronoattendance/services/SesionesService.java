package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
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
        return repo.findAll(Sort.by(DESC, "id"));
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
