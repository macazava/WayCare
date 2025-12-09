package com.example.waycare.Controller;

import DTO.LocalizacaoCreateDTO;
import DTO.LocalizacaoResponseDTO;
import com.example.waycare.Service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<LocalizacaoResponseDTO> listarTodas() {
        return localizacaoService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<LocalizacaoResponseDTO> criar(
            @Valid @RequestBody LocalizacaoCreateDTO dto) {

        return ResponseEntity.ok(localizacaoService.criar(dto));
    }
}



