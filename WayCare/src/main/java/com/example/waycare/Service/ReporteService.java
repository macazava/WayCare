package com.example.waycare.Service;

import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    public Optional<Reporte> procurarPorId(Long id) {
        return reporteRepository.findById(id);
    }

    public Reporte criar(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public Reporte atualizar(Long id, Reporte reporte) {
        Reporte existente = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        existente.setData(reporte.getData());
        existente.setEstado(reporte.getEstado());
        existente.setLocalizacao(reporte.getLocalizacao());
        existente.setObstaculo(reporte.getObstaculo());
        existente.setUtilizador(reporte.getUtilizador());
        return reporteRepository.save(existente);
    }

    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}

