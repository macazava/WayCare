package com.example.waycare.Service;


import com.example.waycare.Repository.FotografiaRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.models.Fotografia;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FotografiaService {

    @Autowired
    private FotografiaRepository fotografiaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Fotografia> listarTodas() {
        return fotografiaRepository.findAll();
    }

    public List<Fotografia> listarPorReporte(Long repId) {
        return fotografiaRepository.findByReporteId(repId);
    }

    public Fotografia criar(Fotografia foto) {
        // Associa o reporte
        Reporte r = reporteRepository.findById(foto.getReporte().getId())
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        foto.setReporte(r);

        // Associa o utilizador, se houver
        if (foto.getUtilizador() != null && foto.getUtilizador().getId() != null) {
            foto.setUtilizador(foto.getUtilizador());
        } else {
            foto.setUtilizador(r.getUtilizador()); // usuário do reporte
        }

        // Associa a anomalia, se houver
        if (foto.getAnomalia() != null && foto.getAnomalia().getId() != null) {
            foto.setAnomalia(foto.getAnomalia());
        } else {
            foto.setAnomalia(r.getAnomalia()); // anomalia do reporte
        }

        // Define data de upload
        foto.setDataUpload(LocalDateTime.now());

        return fotografiaRepository.save(foto);
    }

}


