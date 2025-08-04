package com.cronoattendance.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "horarios_empleados", uniqueConstraints = @UniqueConstraint(columnNames = { "id_empleado",
        "ACTIVO_desde" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorariosEmpleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_horario")
    private Horarios horario;

    @Column(name = "ACTIVO_DESDE", nullable = false)
    private LocalDate activoDesde;

    @Column(name = "ACTIVO_HASTA")
    private LocalDate activoHasta;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;
}
