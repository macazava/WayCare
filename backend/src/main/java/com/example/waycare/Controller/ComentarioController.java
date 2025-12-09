package com.example.waycare.Controller;

import DTO.ComentarioDTO;
import com.example.waycare.Service.ComentarioService;
import com.example.waycare.models.Comentario;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> listarTodos() { return comentarioService.listarTodos(); }


    @GetMapping("/utilizador/{utiId}")
    public List<Comentario> listarPorUtilizador(@PathVariable Long utiId) {
        return comentarioService.listarPorUtilizador(utiId);
    }

    @GetMapping("/reporte/{repId}")
    public List<Comentario> listarPorReporte(@PathVariable Long repId) {
        return comentarioService.listarPorReporte(repId);
    }

    @PostMapping
    public ResponseEntity<Comentario> criar(@Valid @RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.criar(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Comentario> editar(@PathVariable Long id, @Valid @RequestBody Comentario novosDados) {
        return ResponseEntity.ok(comentarioService.editar(id, novosDados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
