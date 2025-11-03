package service;

import com.waycare.waycare2.Model.Utilizador;
import com.waycare.waycare2.Repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

public interface UtilizadorService {
    Utilizador registar(String nome, String email, String password);

    @Service
    public class utilizadorService {

        private final UtilizadorRepository utilizadorRepository;

        private utilizadorService(UtilizadorRepository utilizadorRepository ){
            this.utilizadorRepository = utilizadorRepository;
        }

        public Utilizador registar(String nome, String email, String password) {
            Utilizador utilizador = new Utilizador (nome, email, password);
            return utilizadorRepository.save(utilizador);

        }

    }


    }

