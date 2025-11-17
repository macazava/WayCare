package com.example.waycare.Controller;

import com.example.waycare.Service.NotificacaoService;
import com.example.waycare.models.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
@CrossOrigin(origins = "*")
public class NotificacaoController {
    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/utilizador/{utilizadorId}")
    public ResponseEntity<List<Notificacao>> listarPorUtilizador(@PathVariable Long utilizadorId) {
        return ResponseEntity.ok(notificacaoService.listarPorUtilizador(utilizadorId));
    }

    @PostMapping
    public ResponseEntity<Notificacao> criar(@RequestBody Notificacao notificacao) {
        return ResponseEntity.ok(notificacaoService.criar(notificacao));
    }

    @PutMapping("/{id}/lida")
    public ResponseEntity<Notificacao> marcarComoLida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.marcarComoLida(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacaoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
