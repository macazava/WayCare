package Controller;

import com.waycare.waycare2.Model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class ControllerCategoria {
    private final CategoriaService categoriaService;

    @Autowired
    public ControllerCategoria(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria) {
        Categoria nova = categoriaService.criar(categoria);
        return ResponseEntity.status(201).body(nova);
    }

    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaService.listarTodas();
    }
}
