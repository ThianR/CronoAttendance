package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.DatosBiometricosEmpleados;

@Repository
public interface DatosBiometricosEmpleadosRepository extends JpaRepository<DatosBiometricosEmpleados, Long> {
}
