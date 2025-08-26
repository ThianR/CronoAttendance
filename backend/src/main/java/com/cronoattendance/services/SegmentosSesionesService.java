package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
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
        return repo.findAll(Sort.by(DESC, "id"));
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
