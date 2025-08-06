package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.HorariosEmpleados;

@Repository
public interface HorariosEmpleadosRepository extends JpaRepository<HorariosEmpleados, Integer> {
}
