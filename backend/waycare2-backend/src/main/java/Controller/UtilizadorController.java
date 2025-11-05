package Controller;

import com.waycare.waycare2.Model.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UtilizadorService;

import java.util.List;
@RequestMapping("/api/utilizadores")
public class UtilizadorController {
    private final UtilizadorService utilizadorService;

    @Autowired
    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @PostMapping
    public ResponseEntity<Utilizador> criar(@RequestBody Utilizador utilizador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilizadorService.criar(utilizador));
    }

    @GetMapping
    public List<Utilizador> listarTodos() {
        return utilizadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> buscarPorId(@PathVariable Long id) {
        return utilizadorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        utilizadorService.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
