package com.cronoattendance.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Asistencias;

@Repository
public interface AsistenciasRepository extends JpaRepository<Asistencias, Integer> {
    /**
     * Recupera todas las marcaciones de un empleado, ordenadas por fechaHora
     * ascendente.
     */
    @Query("SELECT a FROM Asistencias a WHERE a.empleado.id = :idEmpleado ORDER BY a.fechaHora")
    List<Asistencias> findByIdEmpleadoOrderByFechaHora(@Param("idEmpleado") Integer idEmpleado);
}
