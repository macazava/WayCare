package com.example.waycare.Service;

import DTO.LocalizacaoCreateDTO;
import DTO.LocalizacaoResponseDTO;
import com.example.waycare.Repository.LocalizacaoRepository;
import com.example.waycare.config.utils.GoogleMapsUtil;
import com.example.waycare.models.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizacaoService {

    @Autowired private LocalizacaoRepository localizacaoRepository;
    @Autowired private GoogleMapsUtil google;

    public LocalizacaoResponseDTO criar(LocalizacaoCreateDTO dto) {

        String endereco = google.getAddressFromCoordinates(
                dto.getLatitude(),
                dto.getLongitude()
        );

        Localizacao loc = new Localizacao();
        loc.setLatitude(dto.getLatitude());
        loc.setLongitude(dto.getLongitude());
        loc.setEndereco(endereco);
        loc.setDescricao(dto.getDescricao());

        loc = localizacaoRepository.save(loc);

        return mapToDTO(loc);
    }

    public List<LocalizacaoResponseDTO> listarTodas() {
        return localizacaoRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private LocalizacaoResponseDTO mapToDTO(Localizacao loc) {
        LocalizacaoResponseDTO dto = new LocalizacaoResponseDTO();
        dto.setId(loc.getId());
        dto.setLatitude(loc.getLatitude());
        dto.setLongitude(loc.getLongitude());
        dto.setEndereco(loc.getEndereco());
        dto.setDescricao(loc.getDescricao());
        return dto;
    }
}

