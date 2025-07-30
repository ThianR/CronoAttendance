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

import com.cronoattendance.entities.SegmentosHorarios.TipoSegmento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "segmentos_sesiones")
public class SegmentosSesiones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_sesion")
    private Sesiones sesiones;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String inicio;

    @Column(nullable = false)
    private String fin;

    @Column(name = "tipo_segmento")
    @Enumerated(EnumType.STRING)
    private TipoSegmento tipoSegmento;
}