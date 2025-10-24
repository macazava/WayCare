package com.example.waycare.Service;

import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.Repository.ObstaculoRepository;
import com.example.waycare.models.Reporte;
import com.example.waycare.models.Utilizador;
import com.example.waycare.models.Obstaculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private ObstaculoRepository obstaculoRepository;

    // Criar um novo reporte
    public Reporte criar(Long utiId, Long obsId, Reporte reporte) {
        Utilizador utilizador = utilizadorRepository.findById(utiId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        Obstaculo obstaculo = obstaculoRepository.findById(obsId)
                .orElseThrow(() -> new RuntimeException("Obstáculo não encontrado"));

        reporte.setUtilizador(utilizador);
        reporte.setObstaculo(obstaculo);
        return reporteRepository.save(reporte);
    }

    // Listar todos os reportes
    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    // Listar reportes por utilizador
    public List<Reporte> listarPorUtilizador(Long utiId) {
        Utilizador utilizador = utilizadorRepository.findById(utiId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        return reporteRepository.findByUtilizador(utilizador);
    }

    // Atualizar estado
    public Reporte atualizarEstado(Long id, String novoEstado) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        reporte.setEstado(novoEstado);
        return reporteRepository.save(reporte);
    }

    // Procurar por ID
    public Optional<Reporte> procurarPorId(Long id) {
        return reporteRepository.findById(id);
    }

    // Eliminar reporte
    public void eliminar(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte não encontrado");
        }
        reporteRepository.deleteById(id);
    }
}

