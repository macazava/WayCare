    package com.example.waycare.Controller;

    import com.example.waycare.Service.AnomaliaService;
    import com.example.waycare.models.Anomalia;
    import jakarta.validation.Valid;
    import java.util.List;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/anomalias")
    public class AnomaliaController {

      @Autowired private AnomaliaService anomaliaService;

      @GetMapping
      public List<Anomalia> listarTodas() {
        return anomaliaService.listarTodas();
      }

      @GetMapping("/{id}")
      public ResponseEntity<Anomalia> procurarPorId(@PathVariable Long id) {
        return anomaliaService
            .procurarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
      }

      @PostMapping
      public ResponseEntity<Anomalia> criar(@Valid @RequestBody Anomalia anomalia) {
        return ResponseEntity.ok(anomaliaService.criar(anomalia));
      }

      @PutMapping("/{id}")
      public ResponseEntity<Anomalia> atualizar(
          @PathVariable Long id, @Valid @RequestBody Anomalia novosDados) {
        return ResponseEntity.ok(anomaliaService.atualizar(id, novosDados));
      }

      @DeleteMapping("/{id}")
      public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        anomaliaService.eliminar(id);
        return ResponseEntity.noContent().build();
      }
    }
