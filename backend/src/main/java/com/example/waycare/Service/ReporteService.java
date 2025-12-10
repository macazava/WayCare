package com.example.waycare.Service;

import DTO.ReporteCreateDTO;
import DTO.ReporteResponseDTO;
import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.Repository.LocalizacaoRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Repository.TipoAnomaliaRepository;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private AnomaliaRepository anomaliaRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private TipoAnomaliaRepository tipoAnomaliaRepository;

    @Autowired
    private FotografiaService fotografiaService;


    public List<ReporteResponseDTO> listarTodosDTO() {
        return reporteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<ReporteResponseDTO> procurarDTO(Long id) {
        return reporteRepository.findById(id)
                .map(this::mapToDTO);
    }

    public List<ReporteResponseDTO> listarPorEstado(String estado) {
        EstadoReporte estadoEnum = EstadoReporte.valueOf(estado);
        return reporteRepository.findByEstadoReporte(estadoEnum)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ReporteResponseDTO> listarPorUtilizador(Long utiId) {
        return reporteRepository.findByUtilizadorId(utiId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ReporteResponseDTO> listarPorAnomalia(Long anoId) {
        return reporteRepository.findByAnomaliaId(anoId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<ReporteResponseDTO> listarPorLocalizacao(Long locId) {
        return reporteRepository.findByLocalizacaoId(locId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ================= CRIAR REPORT COM TIPO ANOMALIA =================
    public ReporteResponseDTO criarDesdeDTO(ReporteCreateDTO dto) {

        // 1️⃣ Buscar Utilizador
        Utilizador u = utilizadorRepository.findById(dto.getUtilizadorId())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        // 2️⃣ Buscar Tipo de Anomalia
        TipoAnomalia tipo = tipoAnomaliaRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de anomalia não encontrado"));

        // 3️⃣ Criar Localizacao automaticamente
        Localizacao loc = new Localizacao();
        loc.setLatitude(dto.getLatitude());
        loc.setLongitude(dto.getLongitude());
        loc.setEndereco(dto.getEndereco() != null ? dto.getEndereco() : "Endereço não disponível");
        localizacaoRepository.save(loc);

        // 4️⃣ Criar Anomalia automaticamente e associar utilizador e localizacao
        Anomalia a = new Anomalia();
        a.setTipo(tipo);
        a.setDescricao(dto.getDescricao());
        a.setGrauPerigo(dto.getGrauPerigo());
        a.setDataRegisto(LocalDateTime.now());
        a.setEstado("PENDENTE");

        // ✅ Associa utilizador e localização
        a.setUtilizador(u);
        a.setLocalizacao(loc);

        anomaliaRepository.save(a);

        // 5️⃣ Criar o Reporte
        Reporte r = new Reporte();
        r.setUtilizador(u);
        r.setAnomalia(a);
        r.setLocalizacao(loc);
        r.setDescricao(dto.getDescricao());
        r.setFotoUrl(dto.getFotoUrl());
        r.setTipoPersonalizado(dto.getTipoPersonalizado());
        r.setEstadoReporte(EstadoReporte.PENDENTE);
        r.setDataRegisto(LocalDateTime.now());
        r.setZona(dto.getZona());
        r.setGrauPerigo(dto.getGrauPerigo());

        r = reporteRepository.save(r);

        // 6️⃣ Criar Fotografia automaticamente, se houver fotoUrl
        if (dto.getFotoUrl() != null && !dto.getFotoUrl().isEmpty()) {
            Fotografia foto = new Fotografia();
            foto.setUrl(dto.getFotoUrl());
            foto.setNome("Foto do Reporte " + r.getId());
            foto.setDescricao("Foto associada ao reporte " + r.getId());
            foto.setReporte(r); // associa o reporte
            // não precisa setar utilizador e anomalia manualmente, o serviço cuida disso
            fotografiaService.criar(foto);
        }
        return mapToDTO(r);
    }

    // ================================================================

    public ReporteResponseDTO atualizar(Long id, Reporte novosDados) {
        Reporte r = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));

        if (novosDados.getDescricao() != null)
            r.setDescricao(novosDados.getDescricao());

        if (novosDados.getEstadoReporte() != null)
            r.setEstadoReporte(novosDados.getEstadoReporte());

        r = reporteRepository.save(r);
        return mapToDTO(r);
    }

    public void eliminar(Long id) {
        if (!reporteRepository.existsById(id))
            throw new RuntimeException("ID inválido");

        reporteRepository.deleteById(id);
    }

    private ReporteResponseDTO mapToDTO(Reporte r) {
        ReporteResponseDTO dto = new ReporteResponseDTO();

        dto.setId(r.getId());

        if (r.getUtilizador() != null) {
            dto.setUtilizadorId(r.getUtilizador().getId());
            dto.setNomeUtilizador(r.getUtilizador().getNome());
        }

        if (r.getAnomalia() != null) {
            dto.setAnomaliaId(r.getAnomalia().getId());
            dto.setNomeAnomalia(r.getAnomalia().getDescricao());
        }

        if (r.getLocalizacao() != null) {
            dto.setLocalizacaoId(r.getLocalizacao().getId());
            dto.setLatitude(r.getLocalizacao().getLatitude());
            dto.setLongitude(r.getLocalizacao().getLongitude());
            dto.setEndereco(r.getLocalizacao().getEndereco());
            dto.setDescricaoLocalizacao(r.getLocalizacao().getDescricao());
        }

        dto.setFotoUrl(r.getFotoUrl());
        dto.setEstadoReporte(r.getEstadoReporte().name());
        dto.setDescricao(r.getDescricao());
        dto.setTipoPersonalizado(r.getTipoPersonalizado());
        dto.setDataRegisto(r.getDataRegisto());
        dto.setZona(r.getZona().name());
        dto.setGrauPerigo(r.getGrauPerigo().name());

        if (r.getRotaAlternativa() != null)
            dto.setRotaAlternativaId(r.getRotaAlternativa().getId());

        return dto;
    }
}

