package com.cronoattendance.entities;

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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "horarios_empleados", uniqueConstraints = @UniqueConstraint(columnNames = { "id_empleado",
        "activo_desde" }))
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

    @Column(name = "activo_desde", nullable = false)
    private String activoDesde;

    @Column(name = "activo_hasta")
    private String activoHasta;
}