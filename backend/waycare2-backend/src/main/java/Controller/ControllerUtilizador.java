package Controller;

import com.waycare.waycare2.Model.Utilizador;
import com.waycare.waycare2.dto.RegistarRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UtilizadorService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utilizadores")
public class ControllerUtilizador {

    private final UtilizadorService utilizadorService;

public ControllerUtilizador(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @PostMapping("/registar")
    public ResponseEntity<?> registar(@RequestBody RegistarRequest request) {
        Utilizador u = utilizadorService.registar(
                request.getNome(),
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(Map.of(
                "id", u.getId(),
                "nome", u.getNome(),
                "email", u.getEmail()
        ));
    }
    @GetMapping
    public List<Utilizador> listarTodos() {
        return utilizadorService.listarTodos();

     }

}






