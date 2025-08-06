package com.cronoattendance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cronoattendance.entities.SegmentosHorarios;

@Repository
public interface SegmentosHorariosRepository extends JpaRepository<SegmentosHorarios, Integer> {
}
