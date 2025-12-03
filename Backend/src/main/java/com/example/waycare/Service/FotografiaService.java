package com.example.waycare.Service;


import com.example.waycare.Repository.FotografiaRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.models.Fotografia;
import com.example.waycare.models.Reporte;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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
        Reporte r = reporteRepository.findById(foto.getReporte().getId())
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        foto.setReporte(r);

        return fotografiaRepository.save(foto);
    }
}


