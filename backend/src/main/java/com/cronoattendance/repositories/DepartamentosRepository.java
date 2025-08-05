package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Departamentos;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Long> {
}
