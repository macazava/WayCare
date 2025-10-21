package Service;
import models.Utilizador;
import repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizadorService {
    private final UtilizadorRepository repo;

    public UtilizadorService(UtilizadorRepository repo) {
        this.repo = repo;
    }

    public List<Utilizador> listarTodos() {
        return repo.findAll();
    }

    public Utilizador criar(Utilizador u) {
        return repo.save(u);
    }
}
