package Controller;

import aj.org.objectweb.asm.commons.Remapper;
import com.waycare.waycare2.Model.obstaculo;
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
    public ResponseEntity<List<obstaculo>> listarTodos() {
        return ResponseEntity.ok(obstaculoService.listarTodos());
    }

    // Criar novo obstáculo
    @PostMapping
    public ResponseEntity<obstaculo> criar(@RequestBody obstaculo obstaculo) {
        obstaculo criado = obstaculoService.criar(obstaculo);
        return new ResponseEntity<>(criado, HttpStatus.CREATED);
    }

    // Buscar obstáculo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Remapper> buscarPorId(@PathVariable Long id) {
        Remapper obstaculo = obstaculoService.buscarPorId(id);
        return ResponseEntity.ok(obstaculo);
    }

    // Deletar obstáculo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        obstaculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
