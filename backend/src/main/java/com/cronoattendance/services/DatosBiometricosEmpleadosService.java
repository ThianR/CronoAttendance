package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.DatosBiometricosEmpleados;
import com.cronoattendance.repositories.DatosBiometricosEmpleadosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatosBiometricosEmpleadosService {
    private final DatosBiometricosEmpleadosRepository repo;

    public List<DatosBiometricosEmpleados> findAll() {
        return repo.findAll(Sort.by(DESC, "id"));
    }

    public DatosBiometricosEmpleados findById(Integer id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public DatosBiometricosEmpleados save(DatosBiometricosEmpleados d) {
        return repo.save(d);
    }

    @Transactional
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
