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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cronoattendance.entities.enums.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departamentos", uniqueConstraints = @UniqueConstraint(columnNames = { "id_empresa", "codigo" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa")
    private Empresas empresa;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL)
    private List<Empleados> Listempleados;

    @PrePersist
    void prePersist() {
        if (estado == null)
            estado = Estado.ACTIVO;
        if (fechaAlta == null)
            fechaAlta = java.time.LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.fechaMod = java.time.LocalDateTime.now();
    }
}
