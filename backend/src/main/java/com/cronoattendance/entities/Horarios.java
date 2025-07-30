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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "horarios", uniqueConstraints = @UniqueConstraint(columnNames = { "id_empresa", "descripcion" }))
public class Horarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empresa")
    private Empresas empresa;

    @Column(nullable = false)
    private String descripcion;

    @Column(name = "inicio_jornada", nullable = false)
    private String inicioJornada;

    @Column(name = "fin_jornada", nullable = false)
    private String finJornada;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @OneToMany(mappedBy = "horarios", cascade = CascadeType.ALL)
    private List<SegmentosHorarios> segmentosHorarios = new ArrayList<>();

    @OneToMany(mappedBy = "horarios", cascade = CascadeType.ALL)
    private List<HorariosEmpleados> horariosEmpleados = new ArrayList<>();

    enum Estado {
        Activo, Inactivo
    }
}
