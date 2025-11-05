package service;

import com.waycare.waycare2.Model.Utilizador;
import com.waycare.waycare2.Repository.UtilizadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UtilizadorService {
    private UtilizadorRepository utilizadorRepository;

    public Utilizador criar(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }
    public Utilizador registar(String nome, String email, String password) {
        Utilizador utilizador = new Utilizador();
        utilizador.setNome(nome);
        utilizador.setEmail(email);
        utilizador.setPassword(password);
        return utilizadorRepository.save(utilizador);
    }

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> buscarPorId(Long id) {
        return utilizadorRepository.findById(id);
    }

    public void apagar(Long id) {
        utilizadorRepository.deleteById(id);
    }
     }




