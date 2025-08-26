package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Horarios;
import com.cronoattendance.repositories.HorariosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HorariosService {
    private final HorariosRepository repo;

    public List<Horarios> findAll() {
        return repo.findAll(Sort.by(DESC, "id"));
    }

    public Horarios findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Horarios save(Horarios h) {
        return repo.save(h);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
