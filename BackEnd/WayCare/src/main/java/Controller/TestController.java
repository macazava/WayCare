package Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import models.Utilizador;
import repository.UtilizadorRepository;

@RestController
@RequestMapping("/api/utilizadores")
public class TestController {

    private final UtilizadorRepository repo;

    public TestController(UtilizadorRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Utilizador> listar() {
        return repo.findAll();
    }
}

