package com.example.waycare.Controller;

import com.example.waycare.Service.ComentarioService;
import com.example.waycare.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")

@CrossOrigin(origins = "*")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public Comentario criarComentario(@RequestBody Comentario comentario) {
        return comentarioService.criarComentario(comentario);
    }
}
