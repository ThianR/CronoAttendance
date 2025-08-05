package com.cronoattendance.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Empresas;
import com.cronoattendance.repositories.EmpresaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresasService {
    private final EmpresaRepository empresaRepo;

    public List<Empresas> findAll() {
        return empresaRepo.findAll();
    }

    public Empresas findById(Long id) {
        return empresaRepo.findById(id).orElseThrow();
    }

    @Transactional
    public Empresas save(Empresas e) {
        return empresaRepo.save(e);
    }

    @Transactional
    public void delete(Long id) {
        empresaRepo.deleteById(id);
    }
}
