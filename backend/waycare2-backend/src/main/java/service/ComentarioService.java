package service;

import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.Notificacao;
import com.waycare.waycare2.Repository.ComentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {
    private ComentarioRepository comentarioRepository;

    public void ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    // Adicionar um novo comentário
    public Notificacao.Comentario adicionarComentario(Notificacao.Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Listar todos os comentários de um utilizador
    public List<Notificacao.Comentario> listarPorUtilizador(Long utilizadorId) {
        return comentarioRepository.findByUtilizador_Id(utilizadorId);
    }

    public Notificacao.Comentario criar(Notificacao.Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public List<Notificacao.Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public List<ComentarioRepository> listarPorReporte(Long reporteId) {
        return comentarioRepository.findByReporte_Id(reporteId);
    }

    public List<Commentator> findByAffiliation_Id(Long affiliationId) {
        return null;
    }

    public void addCommentatorToReport(Long commentatorId, Long reportId) {
    }

    public Commentator findById(Long commentatorId) {
        return null;
    }

    public List<Commentator> listCommentatorsByReportId(Long reportId) {
        return null;
    }

    public List<Commentator> listCommentatorsByAffiliationId(Long affiliationId) {
        return null;
    }

    public void save(Commentator commentator) {
    }

    public List<Commentator> findByReport_Id(Long reportId) {
        return null;
    }
}





