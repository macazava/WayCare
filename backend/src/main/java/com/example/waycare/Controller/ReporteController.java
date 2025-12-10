package com.example.waycare.Controller;

import DTO.ReporteCreateDTO;
import DTO.ReporteResponseDTO;
import com.example.waycare.Service.ReporteService;
import com.example.waycare.models.Reporte;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<ReporteResponseDTO> listarTodos() {
        return reporteService.listarTodosDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> procurarPorId(@PathVariable Long id) {
        return reporteService.procurarDTO(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<ReporteResponseDTO> listarPorEstado(@PathVariable String estado) {
        return reporteService.listarPorEstado(estado);
    }

    @GetMapping("/utilizador/{utiId}")
    public List<ReporteResponseDTO> listarPorUtilizador(@PathVariable Long utiId) {
        return reporteService.listarPorUtilizador(utiId);
    }

    @GetMapping("/anomalia/{anoId}")
    public List<ReporteResponseDTO> listarPorAnomalia(@PathVariable Long anoId) {
        return reporteService.listarPorAnomalia(anoId);
    }

    @GetMapping("/localizacao/{locId}")
    public List<ReporteResponseDTO> listarPorLocalizacao(@PathVariable Long locId) {
        return reporteService.listarPorLocalizacao(locId);
    }

    @PostMapping("/criar")
    public ResponseEntity<ReporteResponseDTO> criarReporte(@RequestBody @Valid ReporteCreateDTO dto) {
        ReporteResponseDTO response = reporteService.criarDesdeDTO(dto);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReporteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody Reporte novosDados) {

        return ResponseEntity.ok(reporteService.atualizar(id, novosDados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
