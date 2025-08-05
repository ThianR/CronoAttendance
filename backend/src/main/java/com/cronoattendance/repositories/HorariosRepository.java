package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.Horarios;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {
}
