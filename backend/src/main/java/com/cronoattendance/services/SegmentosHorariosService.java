package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
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
        return repo.findAll(Sort.by(DESC, "id"));
    }

    public SegmentosHorarios findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public SegmentosHorarios save(SegmentosHorarios sh) {
        return repo.save(sh);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
