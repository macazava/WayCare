package Controller;

import com.waycare.waycare2.Model.Utilizador;
import com.waycare.waycare2.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.LoginService;

import java.util.Optional;

public class LoginController {
    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<Utilizador> utilizador = loginService.autenticar(request.getEmail(), request.getPassword());

        if (utilizador.isPresent()) {
            return ResponseEntity.ok(utilizador.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
