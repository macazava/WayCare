package Controller;

import com.waycare.waycare2.Model.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.ReporteService;

import java.util.ArrayList;
import java.util.List;

public class ReporteController {
    private final List<Reporte> reportes = new ArrayList<>();
    private final ReporteService ReporteService;

    @Autowired
    public ReporteController(ReporteService reporteService) {
        this.ReporteService = reporteService;
    }
    @PostMapping
    public ResponseEntity<Reporte> criar(@RequestBody Reporte reporte) {
        reportes.add(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(reporte);
    }

    @GetMapping
    public List<Reporte> listarTodos() {
        return reportes;
    }
}
