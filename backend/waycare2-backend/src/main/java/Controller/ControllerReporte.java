package Controller;

import com.waycare.waycare2.Model.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReporteService;

import java.util.List;

public class ControllerReporte {
    private ReporteService reporteService;

    @Autowired
    public void ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    public ControllerReporte(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @PostMapping
    public ResponseEntity<Reporte> criar(@RequestBody Reporte reporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.criar(reporte));
    }

    @GetMapping
    public List<Reporte> listarTodos() {
        return reporteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> buscarPorId(@PathVariable Long id) {
        return reporteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/utilizador/{utilizadorId}")
    public List<Reporte> listarPorUtilizador(@PathVariable Long utilizadorId) {
        return reporteService.listarPorUtilizador(utilizadorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        reporteService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
