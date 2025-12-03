package com.example.waycare.Controller;

import com.example.waycare.Service.ZonaService;
import com.example.waycare.models.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zonas")
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    @GetMapping
    public List<Zona> listarTodas() {
        return zonaService.listarTodas();
    }

    @GetMapping("/localizacao/{nome}")
    public List<Zona> listarPorLocalizacao(@PathVariable String nome) {
        return zonaService.listarPorLocalizacao(nome);
    }

    @PostMapping
    public ResponseEntity<Zona> criar(@RequestBody Zona zona) {
        return ResponseEntity.ok(zonaService.criar(zona));
    }
}
