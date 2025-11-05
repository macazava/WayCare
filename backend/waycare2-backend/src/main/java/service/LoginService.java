package service;

import com.waycare.waycare2.Model.Utilizador;

import java.util.List;
import java.util.Optional;

public class LoginService {
    private final List<Utilizador> utilizadores = List.of(
            new Utilizador("Cássia", "cassia@email.com", "segura123"),
            new Utilizador("Luiz", "luiz@email.com", "admin123")
    );

    public Optional<Utilizador> autenticar(String email, String password) {
        return utilizadores.stream()
                .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                .findFirst();
    }
}
