package com.example.waycare.Service;


import com.example.waycare.Repository.FotografiaRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.models.Fotografia;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        Reporte r = reporteRepository.findById(foto.getReporte().getId())
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        foto.setReporte(r);

        return fotografiaRepository.save(foto);
    }
}


