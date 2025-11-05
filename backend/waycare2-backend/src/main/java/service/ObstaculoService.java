package service;


import Controller.ObstaculoController;
import com.waycare.waycare2.Model.Obstaculo;
import com.waycare.waycare2.Repository.ObstaculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstaculoService {

    private final ObstaculoRepository obstaculoRepository;

    public ObstaculoService(ObstaculoRepository obstaculoRepository) {
        this.obstaculoRepository = obstaculoRepository;
    }

    public Obstaculo criar(Obstaculo obstaculo) {
        Obstaculo novo = ObstaculoService.save(obstaculo);
        return ResponseEntity.ok(novo).getBody();

    }

    public List<Obstaculo> listarTodos() {
        return obstaculoRepository.findAll();

    }
    public List<Obstaculo> listarPorCategoria(Long categoriaId) {
        return obstaculoRepository.findByCategoria_Id(categoriaId);
    }

    public void apagar(Long id) {
    }

    public Obstaculo buscarPorId(Long id) {
        return null;
    }

    public ObstaculoController enapce(Long id) {
        return null;
    }

    public static Obstaculo save(Obstaculo existente) {

        return existente;
    }

    public void deletar(Long id) {
        obstaculoRepository.deleteById(id);
    }


    }
