package com.example.waycare.Controller;

import com.example.waycare.Service.NotificacaoService;
import com.example.waycare.models.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public List<Notificacao> listarTodas() {
        return notificacaoService.listarTodas();
    }

    @GetMapping("/utilizador/{utiId}")
    public List<Notificacao> listarPorUtilizador(@PathVariable Long utiId) {
        return notificacaoService.listarPorUtilizador(utiId);
    }

    @PostMapping
    public ResponseEntity<Notificacao> criar(@RequestBody Notificacao notificacao) {
        return ResponseEntity.ok(notificacaoService.criar(notificacao));
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Void> marcarComoLida(@PathVariable Long id) {
        notificacaoService.marcarComoLida(id);
        return ResponseEntity.noContent().build();
    }
}

