// src/main/java/com/cronoattendance/services/AsistenciaService.java
package com.cronoattendance.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronoattendance.entities.Asistencias;
import com.cronoattendance.entities.SegmentosSesiones;
import com.cronoattendance.entities.Sesiones;
import com.cronoattendance.repositories.AsistenciasRepository;
import com.cronoattendance.repositories.SegmentosSesionesRepository;
import com.cronoattendance.repositories.SesionesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsistenciasService {
    private final AsistenciasRepository asistenciaRepo;
    private final SesionesRepository sesionesRepo;
    private final SegmentosSesionesRepository segmentosRepo;

    /**
     * Empareja marcaciones de un empleado y genera sesiones + segmentos diarios.
     */
    @Transactional
    public void procesarMarcaciones(Long idEmpleado) {
        // 1) Obtener todas las marcaciones ordenadas
        List<Asistencias> marc = asistenciaRepo
                .findByIdEmpleadoOrderByFechaHora(idEmpleado);

        // 2) Emparejar y guardar sesiones
        List<Sesiones> sesiones = new ArrayList<>();
        for (int i = 0; i + 1 < marc.size(); i += 2) {
            LocalDateTime ini = marc.get(i).getFechaHora();
            LocalDateTime fin = marc.get(i + 1).getFechaHora();
            Sesiones s = new Sesiones();
            s.setEmpleado(marc.get(i).getEmpleado());
            s.setInicio(ini);
            s.setFin(fin);
            s.setFechaAlta(LocalDateTime.now());
            sesiones.add(sesionesRepo.save(s));
        }

        // 3) Dividir cada sesión por día
        for (Sesiones s : sesiones) {
            List<SegmentosSesiones> segmentos = dividirPorDia(s);
            for (SegmentosSesiones seg : segmentos) {
                segmentosRepo.save(seg);
            }
        }
    }

    /** Lógica de división de sesiones en tramos diarios */
    private List<SegmentosSesiones> dividirPorDia(Sesiones s) {
        List<SegmentosSesiones> out = new ArrayList<>();
        LocalDateTime ini = s.getInicio();
        LocalDateTime fin = s.getFin();
        LocalDate dIni = ini.toLocalDate();
        LocalDate dFin = fin.toLocalDate();

        // caso mismo día
        if (dIni.equals(dFin)) {
            out.add(buildSegmento(s, dIni, ini, fin));
            return out;
        }
        // primer tramo hasta 23:59:59
        out.add(buildSegmento(s, dIni, ini, dIni.atTime(23, 59, 59)));
        // días intermedios
        for (LocalDate d = dIni.plusDays(1); d.isBefore(dFin); d = d.plusDays(1)) {
            out.add(buildSegmento(s, d, d.atStartOfDay(), d.atTime(23, 59, 59)));
        }
        // último tramo desde 00:00
        out.add(buildSegmento(s, dFin, dFin.atStartOfDay(), fin));
        return out;
    }

    private SegmentosSesiones buildSegmento(Sesiones s, LocalDate fecha,
            LocalDateTime ini, LocalDateTime fin) {
        SegmentosSesiones seg = new SegmentosSesiones();
        seg.setSesion(s);
        seg.setFecha(fecha);
        seg.setInicio(ini);
        seg.setFin(fin);
        seg.setFechaAlta(LocalDateTime.now());
        return seg;
    }
}
