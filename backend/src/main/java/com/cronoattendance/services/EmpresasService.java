package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Empresas;
import com.cronoattendance.repositories.EmpresasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresasService {
    private final EmpresasRepository EmpresasRepo;

    public List<Empresas> findAll() {
        return EmpresasRepo.findAll(Sort.by(DESC, "id"));
    }

    public Empresas findById(Integer id) {
        return EmpresasRepo.findById(id).orElseThrow();
    }

    @Transactional
    public Empresas save(Empresas e) {
        return EmpresasRepo.save(e);
    }

    @Transactional
    public void delete(Integer id) {
        EmpresasRepo.deleteById(id);
    }
}
