package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.SegmentosHorarios;
import com.cronoattendance.repositories.SegmentosHorariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SegmentosHorariosService {
    private final SegmentosHorariosRepository repo;

    public List<SegmentosHorarios> findAll() {
        return repo.findAll();
    }

    public SegmentosHorarios findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public SegmentosHorarios save(SegmentosHorarios sh) {
        return repo.save(sh);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
