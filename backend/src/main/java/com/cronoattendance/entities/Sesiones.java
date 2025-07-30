package com.cronoattendance.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sesiones")
public class Sesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_empleado")
    private Empleados empleado;

    @Column(nullable = false)
    private String inicio;

    @Column(nullable = false)
    private String fin;

    @OneToMany(mappedBy = "sesion", cascade = CascadeType.ALL)
    private List<SegmentosSesiones> segmentosSesiones = new ArrayList<>();
}