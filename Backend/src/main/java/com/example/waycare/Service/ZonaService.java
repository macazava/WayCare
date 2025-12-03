package com.example.waycare.Service;

import com.example.waycare.Repository.ZonaRepository;
import com.example.waycare.models.Zona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    public List<Zona> listarTodas() {
        return zonaRepository.findAll();
    }

    public List<Zona> listarPorLocalizacao(String localizacao) {
        return zonaRepository.findByLocalizacao(localizacao);
    }

    public Zona criar(Zona zona) {
        return zonaRepository.save(zona);
    }
}
