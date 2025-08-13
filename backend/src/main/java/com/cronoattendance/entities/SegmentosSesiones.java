package com.cronoattendance.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.cronoattendance.entities.enums.TipoSegmento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "segmentos_sesiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentosSesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sesion")
    private Sesiones sesion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalDateTime inicio;

    @Column(nullable = false)
    private LocalDateTime fin;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_segmento")
    private TipoSegmento tipoSegmento;

    @Column(name = "fecha_alta", nullable = false)
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
