package service;


import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.Reporte;
import com.waycare.waycare2.Repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CommentatorService {

        private final ComentarioService commentatorRepository;
        private final ReporteRepository reportRepository;

        public CommentatorService(ComentarioService commentatorRepository, ReporteRepository reportRepository) {
            this.commentatorRepository = commentatorRepository;
            this.reportRepository = reportRepository;
        }

        public Commentator findById(Long id) {
            return commentatorRepository.findById(id).orElse(null);
        }

        public void addCommentatorToReport(Long commentatorId, Long reportId) {
            Commentator commentator = commentatorRepository.findById(commentatorId).orElse(null);
            Reporte report = reportRepository.findById(reportId).orElse(null).getReporte();

            if (commentator != null && report != null) {
                commentator.setReport(report);
                commentatorRepository.save(commentator);
            }
        }

        public List<Commentator> listCommentatorsByReportId(Long reportId) {
            return commentatorRepository.findByReport_Id(reportId);
        }

        public List<Commentator> listCommentatorsByAffiliationId(Long affiliationId) {
            return commentatorRepository.findByAffiliation_Id(affiliationId);
        }
}
