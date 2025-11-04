package Controller;

import com.waycare.waycare2.Model.Obstaculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ObstaculoService;

import java.util.List;

@RestController
@RequestMapping("/obstaculos")
public class ObstaculoController {

    private final ObstaculoService obstaculoService;

    public ObstaculoController(ObstaculoService obstaculoService) {
        this.obstaculoService = obstaculoService;
    }

    // Listar todos os obstáculos
    @GetMapping
    public ResponseEntity<List<Obstaculo>> listarTodos() {
        return ResponseEntity.ok(obstaculoService.listarTodos());
    }

    // Criar novo obstáculo
    @PostMapping
    public ResponseEntity<Obstaculo> criar(@RequestBody Obstaculo obstaculo) {
        Obstaculo criado = obstaculoService.criar(obstaculo);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
    }

    // Buscar obstáculo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Obstaculo> buscarPorId(@PathVariable Long id) {
        Obstaculo obstaculo = obstaculoService.buscarPorId(id);
        return obstaculo != null
                ? ResponseEntity.ok(obstaculo)
                : ResponseEntity.notFound().build();
    }

    // Deletar obstáculo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        obstaculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
