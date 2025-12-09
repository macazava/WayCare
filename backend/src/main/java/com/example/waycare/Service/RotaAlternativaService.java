package com.example.waycare.Service;

import DTO.PontoIntermedioDTO;
import DTO.RotaAlternativaDTO;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Repository.RotaAlternativaRepository;
import com.example.waycare.models.Reporte;
import com.example.waycare.models.RotaAlternativa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RotaAlternativaService {

    @Autowired
    private RotaAlternativaRepository rotaAlternativaRepository;

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ObjectMapper objectMapper;


    @Transactional
    public RotaAlternativaDTO criarParaReporte(Long reporteId, RotaAlternativaDTO dto) {

        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado."));

        if (reporte.getRotaAlternativa() != null) {
            throw new RuntimeException("Este reporte já tem uma rota alternativa associada.");
        }

        RotaAlternativa rota = mapToEntity(dto);
        rotaAlternativaRepository.save(rota);

        reporte.setRotaAlternativa(rota);
        reporteRepository.save(reporte);

        return mapToDTO(rota);
    }

    public RotaAlternativaDTO obterPorId(Long id) {
        RotaAlternativa rota = rotaAlternativaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rota alternativa não encontrada."));
        return mapToDTO(rota);
    }

    public RotaAlternativaDTO obterPorReporte(Long reporteId) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado."));

        if (reporte.getRotaAlternativa() == null) {
            throw new RuntimeException("O reporte não tem uma rota alternativa associada.");
        }

        return mapToDTO(reporte.getRotaAlternativa());
    }

    @Transactional
    public RotaAlternativaDTO atualizar(Long rotaId, RotaAlternativaDTO dto) {

        RotaAlternativa rota = rotaAlternativaRepository.findById(rotaId)
                .orElseThrow(() -> new RuntimeException("Rota alternativa não encontrada."));

        rota.setNome(dto.getNome());
        rota.setLatitudeInicio(dto.getLatitudeInicio());
        rota.setLongitudeInicio(dto.getLongitudeInicio());
        rota.setLatitudeDestino(dto.getLatitudeDestino());
        rota.setLongitudeDestino(dto.getLongitudeDestino());
        rota.setPontosIntermediosJson(serialize(dto.getPontosIntermedios()));

        rotaAlternativaRepository.save(rota);

        return mapToDTO(rota);
    }

    @Transactional
    public void eliminar(Long rotaId) {
        RotaAlternativa rota = rotaAlternativaRepository.findById(rotaId)
                .orElseThrow(() -> new RuntimeException("Rota alternativa não encontrada."));

        reporteRepository.findByRotaAlternativaId(rotaId)
                .ifPresent(reporte -> {
                    reporte.setRotaAlternativa(null);
                    reporteRepository.save(reporte);
                });

        rotaAlternativaRepository.delete(rota);
    }

    private RotaAlternativa mapToEntity(RotaAlternativaDTO dto) {
        RotaAlternativa rota = new RotaAlternativa();
        rota.setId(dto.getId());
        rota.setNome(dto.getNome());
        rota.setLatitudeInicio(dto.getLatitudeInicio());
        rota.setLongitudeInicio(dto.getLongitudeInicio());
        rota.setLatitudeDestino(dto.getLatitudeDestino());
        rota.setLongitudeDestino(dto.getLongitudeDestino());
        rota.setPontosIntermediosJson(serialize(dto.getPontosIntermedios()));
        return rota;
    }

    private RotaAlternativaDTO mapToDTO(RotaAlternativa rota) {
        RotaAlternativaDTO dto = new RotaAlternativaDTO();
        dto.setId(rota.getId());
        dto.setNome(rota.getNome());
        dto.setLatitudeInicio(rota.getLatitudeInicio());
        dto.setLongitudeInicio(rota.getLongitudeInicio());
        dto.setLatitudeDestino(rota.getLatitudeDestino());
        dto.setLongitudeDestino(rota.getLongitudeDestino());
        dto.setPontosIntermedios(deserialize(rota.getPontosIntermediosJson()));
        return dto;
    }

    private String serialize(List<PontoIntermedioDTO> pontos) {
        try {
            return objectMapper.writeValueAsString(pontos);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter pontos para JSON", e);
        }
    }

    private List<PontoIntermedioDTO> deserialize(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter JSON para pontos", e);
        }
    }
}

