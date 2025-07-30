package com.cronoattendance.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asistencias")
public class Asistencias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Column(nullable = false)
    private String fechaHora;

    @Column(name = "tipo_asistencia", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAsistencia tipoAsistencia;

    @Column(name = "tipo_marcacion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMarcacion tipoMarcacion;

    // Tipos de asistencia y marcación
    enum TipoAsistencia {
        ENTRADA, SALIDA
    }

    enum TipoMarcacion {
        FACIAL, MANUAL
    }
}
