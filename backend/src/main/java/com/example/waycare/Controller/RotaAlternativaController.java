package com.example.waycare.Controller;

import DTO.RotaAlternativaDTO;
import com.example.waycare.Service.RotaAlternativaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rotas")
public class RotaAlternativaController {

    @Autowired
    private RotaAlternativaService rotaAlternativaService;

    @PostMapping("/reporte/{reporteId}")
    public ResponseEntity<RotaAlternativaDTO> criarParaReporte(
            @PathVariable Long reporteId,
            @Valid @RequestBody RotaAlternativaDTO dto) {

        return ResponseEntity.ok(rotaAlternativaService.criarParaReporte(reporteId, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RotaAlternativaDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(rotaAlternativaService.obterPorId(id));
    }

    @GetMapping("/reporte/{reporteId}")
    public ResponseEntity<RotaAlternativaDTO> obterPorReporte(@PathVariable Long reporteId) {
        return ResponseEntity.ok(rotaAlternativaService.obterPorReporte(reporteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RotaAlternativaDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody RotaAlternativaDTO dto) {

        return ResponseEntity.ok(rotaAlternativaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        rotaAlternativaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

