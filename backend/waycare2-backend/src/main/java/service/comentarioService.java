package service;

import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.Notificacao;
import com.waycare.waycare2.Repository.comentarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class comentarioService {
    private comentarioRepository comentarioRepository;

    public void ComentarioService(comentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public comentarioService(comentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    // Adicionar um novo comentário
    public Notificacao.Comentario adicionarComentario(Notificacao.Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    // Listar todos os comentários de um reporte
    public List<comentarioRepository> listarPorReporte(Long reporteId) {
        return comentarioRepository.findByReporte_Id(reporteId);
    }

    // Listar todos os comentários de um utilizador
    public List<Notificacao.Comentario> listarPorUtilizador(Long utilizadorId) {
        return comentarioRepository.findByUtilizador_Id(utilizadorId);
    }

    public void addCommentatorToReport(Long commentatorId, Long reportId) {
    }

    public void save(Commentator commentator) {
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

    public List<Commentator> findByReport_Id(Long reportId) {
        return null;
    }

    public List<Commentator> findByAffiliation_Id(Long affiliationId) {
        return null;
    }
}



