package com.cronoattendance.entities;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cronoattendance.entities.enums.TipoSegmento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "segmentos_horarios", uniqueConstraints = @UniqueConstraint(columnNames = { "id_horario", "segmento" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmentosHorarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_horario")
    private Horarios horario;

    @Column(nullable = false)
    private Integer segmento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_segmento", nullable = false)
    private TipoSegmento tipoSegmento;

    @Column(nullable = false)
    private String inicio;

    @Column(nullable = false)
    private String fin;

    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_mod")
    private LocalDateTime fechaMod;
}