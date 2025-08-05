package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Empresas;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresas, Long> {
}
