package service;


import Controller.ObstaculoController;
import com.waycare.waycare2.Model.Obstaculo;
import com.waycare.waycare2.Repository.obstaculoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstaculoService {

    private final obstaculoRepository obstaculoRepository;

    public ObstaculoService(obstaculoRepository obstaculoRepository) {
        this.obstaculoRepository = obstaculoRepository;
    }

    public Obstaculo criar(Obstaculo obstaculo) {
        Obstaculo novo = ObstaculoService.save(obstaculo);
        return ResponseEntity.ok(novo).getBody();

    }

    public List<Obstaculo> listarTodos() {
        return obstaculoRepository.findAll();
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
