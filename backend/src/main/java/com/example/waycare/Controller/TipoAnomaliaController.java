        package com.example.waycare.Controller;

        import com.example.waycare.Service.TipoAnomaliaService;
        import com.example.waycare.models.TipoAnomalia;
        import jakarta.validation.Valid;
        import java.util.List;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

        @RestController
        @RequestMapping("/api/tipos-anomalia")
        public class TipoAnomaliaController {

          @Autowired private TipoAnomaliaService tipoAnomaliaService;

          @GetMapping
          public List<TipoAnomalia> listarTodos() {
            return tipoAnomaliaService.listarTodos();
          }

          @GetMapping("/{id}")
          public ResponseEntity<TipoAnomalia> procurarPorId(@PathVariable Long id) {
            return tipoAnomaliaService
                .procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
          }

          @PostMapping
          public ResponseEntity<TipoAnomalia> criar(@Valid @RequestBody TipoAnomalia tipo) {
            return ResponseEntity.ok(tipoAnomaliaService.criar(tipo));
          }

          @PutMapping("/{id}")
          public ResponseEntity<TipoAnomalia> atualizar(
              @PathVariable Long id, @Valid @RequestBody TipoAnomalia novosDados) {
            return ResponseEntity.ok(tipoAnomaliaService.atualizar(id, novosDados));
          }

          @DeleteMapping("/{id}")
          public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            tipoAnomaliaService.eliminar(id);
            return ResponseEntity.noContent().build();
          }

          @GetMapping("/search")
          public List<TipoAnomalia> procurarPorNome(@RequestParam String nome) {
            return tipoAnomaliaService.procurarPorNome(nome);
          }
        }
