package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Sesiones;

@Repository
public interface SesionesRepository extends JpaRepository<Sesiones, Integer> {
}
