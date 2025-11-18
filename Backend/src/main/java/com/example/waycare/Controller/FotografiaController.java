package com.example.waycare.Controller;

import com.example.waycare.Service.FotografiaService;
import com.example.waycare.models.Fotografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fotografias")
@CrossOrigin(origins = "*")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFoto(@RequestParam("file") MultipartFile file,
                                        @RequestParam("reporteId") Long reporteId) {
        Fotografia foto = fotografiaService.salvar(file, reporteId);
        return ResponseEntity.ok(foto);
    }

    //Dar uplaod de fotografia no reporte
    @PostMapping("/reporte/{repId}")
    public ResponseEntity<?> uploadFoto(
            @PathVariable Long repId,
            @RequestParam("file") MultipartFile file) {
        try {
            Fotografia nova = fotografiaService.criarComUpload(repId, file);
            return ResponseEntity.status(201).body(nova);
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Erro ao guardar a fotografia: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body("Erro: " + e.getMessage());
        }
    }

    //Listar todas as fotografias
    @GetMapping
    public ResponseEntity<List<Fotografia>> listarTodos() {
        return ResponseEntity.ok(fotografiaService.listarTodos());
    }

    //Procurar fotografias por ID
    @GetMapping("procurar/{id}")
    public ResponseEntity<Fotografia> procurarPorId(@PathVariable Long id) {
        Optional<Fotografia> foto = fotografiaService.procurarPorId(id);
        return foto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Criar fotografia
    @PostMapping
    public ResponseEntity<Fotografia> criar(@RequestBody Fotografia fotografia) {
        Fotografia nova = fotografiaService.criar(fotografia);
        return ResponseEntity.ok(nova);
    }

    //Atualizar fotografia
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Fotografia> atualizar(
            @PathVariable Long id, @RequestBody Fotografia fotografia) {
        try {
            Fotografia atualizada = fotografiaService.atualizar(id, fotografia);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliminar fotografia
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fotografiaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

