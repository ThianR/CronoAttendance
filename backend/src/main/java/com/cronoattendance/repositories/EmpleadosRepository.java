package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Empleados;

@Repository
public interface EmpleadosRepository extends JpaRepository<Empleados, Long> {
}
