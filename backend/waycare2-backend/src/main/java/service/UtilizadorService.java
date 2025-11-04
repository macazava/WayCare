package service;

import com.waycare.waycare2.Model.Utilizador;
import com.waycare.waycare2.Repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UtilizadorService {
    Utilizador registar(String nome, String email, String password);

    List<Utilizador> listarTodos();

    @Service
    public class utilizadorService {

        private final UtilizadorRepository utilizadorRepository;

        public utilizadorService(UtilizadorRepository utilizadorRepository ){
            this.utilizadorRepository = utilizadorRepository;
        }

        public Optional<Utilizador> buscarPorEmail(String email) {
            return null;
        }
         }

     }




