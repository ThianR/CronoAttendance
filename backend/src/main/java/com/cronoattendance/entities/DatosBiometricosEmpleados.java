package com.cronoattendance.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "datos_biometricos_empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosBiometricosEmpleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Lob
    @Column(name = "template_data", nullable = false)
    private byte[] templateData;

    @Column(name = "fecha_alta", nullable = false, updatable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @PrePersist
    void prePersist() {
        if (fechaAlta == null)
            fechaAlta = java.time.LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.fechaMod = java.time.LocalDateTime.now();
    }
}
