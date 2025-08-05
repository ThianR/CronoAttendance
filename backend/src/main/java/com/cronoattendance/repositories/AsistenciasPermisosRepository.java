package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.AsistenciasPermisos;

@Repository
public interface AsistenciasPermisosRepository extends JpaRepository<AsistenciasPermisos, Long> {
}
