package com.example.waycare.Service;

import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Anomalia;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnomaliaService {

  @Autowired private AnomaliaRepository anomaliaRepository;

  public List<Anomalia> listarTodas() {
    return anomaliaRepository.findAll();
  }

  public Optional<Anomalia> procurarPorId(Long id) {
    return anomaliaRepository.findById(id);
  }

  public Anomalia criar(Anomalia anomalia) {
    return anomaliaRepository.save(anomalia);
  }

  public Anomalia atualizar(Long id, Anomalia novosDados) {
    Anomalia a =
        anomaliaRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Anomalia não encontrada"));

    if (novosDados.getDescricao() != null) a.setDescricao(novosDados.getDescricao());
    if (novosDados.getEstado() != null) a.setEstado(novosDados.getEstado());
    if (novosDados.getTipo() != null) a.setTipo(novosDados.getTipo());
    if (novosDados.getGrauPerigo() != null) a.setGrauPerigo(novosDados.getGrauPerigo());

    return anomaliaRepository.save(a);
  }

  public void eliminar(Long id) {
    if (!anomaliaRepository.existsById(id)) throw new RuntimeException("ID não existe");
    anomaliaRepository.deleteById(id);
  }
}
