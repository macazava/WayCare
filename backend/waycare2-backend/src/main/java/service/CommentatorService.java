package service;


import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.reporte;
import com.waycare.waycare2.Repository.reporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CommentatorService {

        private final comentarioService commentatorRepository;
        private final reporteRepository reportRepository;

        public CommentatorService(comentarioService commentatorRepository, reporteRepository reportRepository) {
            this.commentatorRepository = commentatorRepository;
            this.reportRepository = reportRepository;
        }

        public Commentator findById(Long id) {
            return commentatorRepository.findById(id).orElse(null);
        }

        public void addCommentatorToReport(Long commentatorId, Long reportId) {
            Commentator commentator = commentatorRepository.findById(commentatorId).orElse(null);
            reporte report = reportRepository.findById(reportId).orElse(null).getReporte();

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
