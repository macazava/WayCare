package com.example.waycare.Controller;


import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public List<Utilizador> listarTodos() {
        return utilizadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> procurarPorId(@PathVariable Long id) {
        return utilizadorService.procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Utilizador> criar(@RequestBody Utilizador utilizador) {
        return ResponseEntity.ok(utilizadorService.criar(utilizador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> atualizar(@PathVariable Long id, @RequestBody Utilizador novosDados) {
        return ResponseEntity.ok(utilizadorService.atualizar(id, novosDados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        utilizadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    //Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Utilizador loginRequest) {
        boolean autenticado = utilizadorService.autenticar(
                (String) loginRequest.getEmail(), (String) loginRequest.getPassword());

        if (autenticado) {
            return ResponseEntity.ok("Login efetuado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("Email ou password incorretos.");
        }
    }
}



