package com.example.waycare.Controller;

import com.example.waycare.Service.ComentarioService;
import com.example.waycare.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*")
public class ComentarioController {
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<Comentario> criar(@RequestBody Comentario comentario) {
        Comentario novo = comentarioService.criarComentario(comentario);
        return ResponseEntity.ok(novo);
    }
}
