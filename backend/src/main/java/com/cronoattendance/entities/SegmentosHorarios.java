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
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "segmentos_horarios", uniqueConstraints = @UniqueConstraint(columnNames = { "id_horario", "segmento" }))
public class SegmentosHorarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_horario")
    private Horarios horario;

    private Integer segmento;

    @Column(name = "tipo_segmento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSegmento tipoSegmento;

    @Column(nullable = false)
    private String inicio;

    @Column(nullable = false)
    private String fin;

    // Tipos de segmento
    enum TipoSegmento {
        TRABAJO, DESCANSO
    }
}
