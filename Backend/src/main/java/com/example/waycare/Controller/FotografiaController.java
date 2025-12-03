package com.example.waycare.Controller;

import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Service.FotografiaService;
import com.example.waycare.models.Fotografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/fotografias")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;

    @GetMapping
    public List<Fotografia> listarTodas() {
        return fotografiaService.listarTodas();
    }

    @GetMapping("/reporte/{repId}")
    public List<Fotografia> listarPorReporte(@PathVariable Long repId) {
        return fotografiaService.listarPorReporte(repId);
    }

    @PostMapping
    public ResponseEntity<Fotografia> criar(@RequestBody Fotografia foto) {
        return ResponseEntity.ok(fotografiaService.criar(foto));
    }
}


