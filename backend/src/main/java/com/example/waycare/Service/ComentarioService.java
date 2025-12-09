package com.example.waycare.Service;


import DTO.ComentarioDTO;
import com.example.waycare.Repository.ComentarioRepository;
import com.example.waycare.Repository.ReporteRepository;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.models.Comentario;
import com.example.waycare.models.Reporte;
import com.example.waycare.models.Utilizador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
    public class ComentarioService {

        @Autowired private ComentarioRepository comentarioRepository;
        @Autowired private UtilizadorRepository utilizadorRepository;
        @Autowired private ReporteRepository reporteRepository;


    public Comentario criar(ComentarioDTO dto) {

        Utilizador u = utilizadorRepository.findById(dto.getUtilizadorId())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

        Reporte r = reporteRepository.findById(dto.getReporteId())
                .orElseThrow(() -> new RuntimeException("Reporte não encontrado"));

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setUtilizador(u);
        comentario.setReporte(r);

        return comentarioRepository.save(comentario);
    }


    public List<Comentario> listarTodos() { return comentarioRepository.findAll(); }

        public List<Comentario> listarPorUtilizador(Long utiId) { return comentarioRepository.findByUtilizadorId(utiId); }

        public List<Comentario> listarPorReporte(Long repId) { return comentarioRepository.findByReporteId(repId); }

        public Comentario editar(Long id, Comentario novosDados) {
            Comentario c = comentarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

            if(novosDados.getTexto() != null) c.setTexto(novosDados.getTexto());
            return comentarioRepository.save(c);
        }

        public void eliminar(Long id){
            if(!comentarioRepository.existsById(id))
                throw new RuntimeException("Comentário não existe");
            comentarioRepository.deleteById(id);
        }
    }
