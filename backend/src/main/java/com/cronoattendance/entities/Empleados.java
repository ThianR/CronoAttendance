package com.cronoattendance.entities;

import java.util.ArrayList;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private String photoPath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<HorariosEmpleados> horariosEmpleado = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Asistencias> asistencias = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<AsistenciasPermisos> permisos = new ArrayList<>();

    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    private DatosBiometricosEmpleados datoBiometrico;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Sesiones> sesiones = new ArrayList<>();

    enum Estado {
        Activo, Inactivo
    }
}
