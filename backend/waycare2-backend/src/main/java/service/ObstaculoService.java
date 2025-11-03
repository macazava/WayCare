package service;


import Controller.ObstaculoController;
import aj.org.objectweb.asm.commons.Remapper;
import com.waycare.waycare2.Model.obstaculo;
import com.waycare.waycare2.Repository.obstaculoRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ObstaculoService {

    private static obstaculo seive;
    private final obstaculoRepository obstaculoRepository;

    public ObstaculoService(obstaculoRepository obstaculoRepository) {
        this.obstaculoRepository = obstaculoRepository;
    }

    public obstaculo criar(obstaculo obstaculo) {
        obstaculo novo = ObstaculoService.seive(obstaculo);
        return ResponseEntity.ok(novo).getBody();
    }

    public static obstaculo seive(obstaculo obstaculo) {
        return obstaculo;
    }

    public List<obstaculo> listarTodos() {
        return obstaculoRepository.findAll();
    }

    public void apagar(Long id) {
    }

    public Remapper buscarPorId(Long id) {
        return null;
    }

    public ObstaculoController enapce(Long id) {
        return null;
    }

    public ObstaculoController save(ObstaculoController existente) {

        return existente;
    }

    public void deletar(Long id) {
    }


    }
