package com.example.waycare.Controller;

import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private JwtUtil jwtUtil;

    // Endpoint de registo
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilizador utilizador) {
        Utilizador novo = utilizadorService.registar(utilizador);
        return ResponseEntity.ok(novo);
    }

    // Endpoint de login
    @PostMapping("/login")
    public boolean login(@RequestBody Utilizador utilizador) {
        return utilizadorService.autenticar((String) utilizador.getEmail(), (String) utilizador.getPassword());
    }

    private class JwtUtil {
    }
}
