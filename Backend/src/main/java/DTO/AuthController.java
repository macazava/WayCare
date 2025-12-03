package DTO;

import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    @PostMapping("/register")
    public ResponseEntity<Utilizador> register(@RequestBody RegisterRequestDTO dto) {
        Utilizador novo = utilizadorService.registarNovoUtilizador(dto);
        return ResponseEntity.ok(novo);
    }
}
