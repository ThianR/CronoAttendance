// SegmentoSesionRepository.java
package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.SegmentosSesiones;

@Repository
public interface SegmentosSesionesRepository extends JpaRepository<SegmentosSesiones, Integer> {
}
