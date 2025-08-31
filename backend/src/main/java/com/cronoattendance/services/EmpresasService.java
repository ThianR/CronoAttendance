package com.cronoattendance.services;

import static org.springframework.data.domain.Sort.Direction.DESC;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Empresas;
import com.cronoattendance.entities.enums.Estado;
import com.cronoattendance.repositories.EmpresasRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresasService {
    private final EmpresasRepository EmpresasRepo;

    @Transactional(readOnly = true)
    public Page<Empresas> search(String codigo, String descripcion, String estado, Pageable pageable) {
        Empresas probe = new Empresas();
        if (codigo != null && !codigo.isBlank())
            probe.setCodigo(codigo);
        if (descripcion != null && !descripcion.isBlank())
            probe.setDescripcion(descripcion);
        if (estado != null && !estado.isBlank()) {
            try {
                probe.setEstado(Estado.valueOf(estado.toUpperCase()));
            } catch (Exception ignored) {
            }
        }

        ExampleMatcher matcher = ExampleMatcher.matchingAll()
                .withIgnoreNullValues()
                .withMatcher("codigo", m -> m.contains().ignoreCase())
                .withMatcher("descripcion", m -> m.contains().ignoreCase());

        return EmpresasRepo.findAll(Example.of(probe, matcher), pageable);
    }

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
