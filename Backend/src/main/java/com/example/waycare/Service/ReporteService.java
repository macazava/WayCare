package com.example.waycare.Service;

import com.example.waycare.Repository.ReporteRepository;

import com.example.waycare.models.*;

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

    public Reporte atualizar(Long id, Reporte novosDados) {
        Reporte r = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        r.setDescricao(novosDados.getDescricao());
        r.setEstado(novosDados.getEstado());
        return reporteRepository.save(r);
    }


    public void eliminar(Long id) {
        reporteRepository.deleteById(id);
    }
}
