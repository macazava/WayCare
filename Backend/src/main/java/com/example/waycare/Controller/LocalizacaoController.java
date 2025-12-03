package com.example.waycare.Controller;

import com.example.waycare.Service.LocalizacaoService;
import com.example.waycare.models.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<Localizacao> listarTodas() {
        return localizacaoService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Localizacao> criar(@RequestBody Localizacao loc) {
        return ResponseEntity.ok(localizacaoService.criar(loc));
    }
}


