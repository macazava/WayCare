package DTO;

import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("API está ativa");
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto) {
        Utilizador novo = utilizadorService.registarNovoUtilizador(dto);
        return ResponseEntity.status(201).body(novo);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilizador utilizador) {
        boolean autenticado = utilizadorService.autenticar(utilizador.getEmail(), utilizador.getPassword());

        if (autenticado) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(401).body(false);
        }
    }

}
