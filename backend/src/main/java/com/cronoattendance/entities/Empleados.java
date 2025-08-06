// src/main/java/com/cronoattendance/entities/Empleado.java
package com.cronoattendance.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cronoattendance.entities.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamentos departamento;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true)
    private String email;

    @Column(name = "photo_path")
    private String photoPath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<HorariosEmpleados> horariosEmpleados;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Asistencias> asistencias;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<AsistenciasPermisos> permisos;

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private DatosBiometricosEmpleados datosBiometricos;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Sesiones> sesiones;
}
