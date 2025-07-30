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
@Table(name = "asistencias_permisos")
public class AsistenciasPermisos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PermisoTipo tipo;

    @Column(nullable = false)
    private String inicio;

    @Column(nullable = false)
    private String fin;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PermisoEstado estado;

    @Column(name = "fecha_solicitud", nullable = false)
    private String fechaSolicitud;

    @Column(name = "fecha_aprobacion")
    private String fechaAprobacion;

    @ManyToOne
    @JoinColumn(name = "id_empleado_aprobador")
    private Empleados empleadoAprobador;

    // Permiso enums
    enum PermisoTipo {
        VACACION, REPOSO, SALIDA_ANTICIPADA, ENTRADA_TARDIA, OTRO
    }

    enum PermisoEstado {
        PENDIENTE, APROBADO, RECHAZADO
    }
}
