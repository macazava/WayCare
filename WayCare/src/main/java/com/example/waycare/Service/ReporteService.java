package com.example.waycare.Service;

import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Reporte;
import com.example.waycare.models.Utilizador;
import com.example.waycare.models.Anomalia;
import com.example.waycare.models.Localizacao;
import com.example.waycare.utils.GoogleMapsUtil;
import com.example.waycare.exceptions.EnderecoNaoEncontradoException;
import com.example.waycare.exceptions.GoogleMapsApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private GoogleMapsUtil googleMapsUtil;

    public Reporte criar(Long utiId, Long anoId, Reporte reporte) {

        Utilizador utilizador = utilizadorRepository.findById(utiId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        reporte.setUtilizador(utilizador);
        reporte.setEstado("Pendente");
        reporte.setData(LocalDate.now());

        if (anoId != null && anoId > 0) {
            Anomalia anomalia = anomaliaRepository.findById(anoId)
                    .orElseThrow(() -> new RuntimeException("Anomalia não encontrada"));
            reporte.setAnomalia(anomalia);
            reporte.setTipoPersonalizado(null);
        }
        // "outro" isto ainda está em teste
        else if (reporte.getTipoPersonalizado() != null && !reporte.getTipoPersonalizado().isEmpty()) {
            reporte.setAnomalia(null);
        }
        else {
            throw new RuntimeException("Tipo de anomalia não especificado");
        }
        // Localização via Google Maps API
        if (reporte.getLocalizacao() != null) {
            Localizacao loc = reporte.getLocalizacao();

            // há coordenadas e queremos morada obter morada
            if (loc.getLatitude() != null && loc.getLongitude() != null) {
                try {
                    String morada = googleMapsUtil.getAddressFromCoordinates(
                            loc.getLatitude(),
                            loc.getLongitude()
                    );
                    loc.setEndereco(morada);
                } catch (EnderecoNaoEncontradoException | GoogleMapsApiException e) {
                    throw e;
                } catch (Exception e) {
                    throw new GoogleMapsApiException("Erro inesperado ao obter morada do Google Maps", e);
                }
            }
            // Temos a morada e queremos obter as coordenadas
            else if (loc.getEndereco() != null) {
                try {
                    double[] coords = googleMapsUtil.getCoordinatesFromAddress(loc.getEndereco());
                    loc.setLatitude(coords[0]);
                    loc.setLongitude(coords[1]);
                } catch (EnderecoNaoEncontradoException | IllegalArgumentException | GoogleMapsApiException e) {
                    throw e;
                } catch (Exception e) {
                    throw new GoogleMapsApiException("Erro inesperado ao obter coordenadas do Google Maps", e);
                }
            }

            // validação final da localização: se existir localizacao, tem de ter lat/long válidos
            if ((loc.getLatitude() == null || loc.getLongitude() == null)) {
                throw new EnderecoNaoEncontradoException("Localização inválida: latitude/longitude em falta");
            }
            reporte.setLocalizacao(loc);
        }
        // Guardar na base de dados
        return reporteRepository.save(reporte);
    }
    // Listar todos os reportes
    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }
    // LIistar por utilizadores
    public List<Reporte> listarPorUtilizador(Long utiId) {
        Utilizador utilizador = utilizadorRepository.findById(utiId)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        return reporteRepository.findByUtilizador(utilizador);
    }
    //Filtrar por texto (não está a dar certo)
    public List<Reporte> listarPorTipo(String tipo) {
        return reporteRepository.searchByTipoOrCustom(tipo);
    }

    public Reporte atualizarEstado(Long id, String novoEstado) {
        if (novoEstado == null || novoEstado.isBlank()) {
            throw new IllegalArgumentException("Estado inválido");
        }
        String estadoNormalizado = switch (novoEstado.trim().toLowerCase()) {
            case "pendente" -> "Pendente";
            case "resolvido", "resolvida" -> "Resolvido";
            case "em análise", "em analise", "analise" -> "Em análise";
            default -> throw new IllegalArgumentException("Estado desconhecido: " + novoEstado);
        };
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
        reporte.setEstado(estadoNormalizado);
        return reporteRepository.save(reporte);
    }

    public Optional<Reporte> procurarPorId(Long id) {
        return reporteRepository.findById(id);
    }

    public void eliminar(Long id) {
        if (!reporteRepository.existsById(id)) {
            throw new RuntimeException("Reporte não encontrado");
        }
        reporteRepository.deleteById(id);
    }
    public Reporte obterDetalhe(Long id) {
        return  reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));
    }
}
