package com.example.waycare.Controller;

import com.example.waycare.Service.ComentarioService;
import com.example.waycare.Service.ComentarioService;
import com.example.waycare.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> listarTodos() {
        return comentarioService.listarTodos();
    }

    @GetMapping("/anomalia/{anoId}")
    public List<Comentario> listarPorAnomalia(@PathVariable Long anoId) {
        return comentarioService.listarPorAnomalia(anoId);
    }

    @GetMapping("/utilizador/{utiId}")
    public List<Comentario> listarPorUtilizador(@PathVariable Long utiId) {
        return comentarioService.listarPorUtilizador(utiId);
    }

    @PostMapping
    public ResponseEntity<Comentario> criar(@RequestBody Comentario comentario) {
        return ResponseEntity.ok(comentarioService.criar(comentario));
    }
}
