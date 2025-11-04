package Controller;

import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.Notificacao;
import com.waycare.waycare2.Model.comentario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.comentarioService;

import java.util.List;

@RestController
@RequestMapping("/commentators")
public class CommentatorController {

    private final comentarioService commentatorService;

    public CommentatorController(comentarioService commentatorService) {
        this.commentatorService = commentatorService;
    }

    // Adicionar um comentarista a um report
    @PostMapping("/addCommentatorToReport")
    public ResponseEntity<Void> addCommentatorToReport(@RequestParam Long reportId,
                                                       @RequestParam Long commentatorId) {
        Commentator commentator = commentatorService.findById(commentatorId);
        if (commentator != null) {
            commentatorService.addCommentatorToReport(commentatorId, reportId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Listar comentaristas por report
    @GetMapping("/getCommentatorsByReportId")
    public ResponseEntity<List<Commentator>> getCommentatorsByReportId(@RequestParam Long reportId) {
        return ResponseEntity.ok(commentatorService.listCommentatorsByReportId(reportId));
    }

    // Listar comentaristas por afiliação
    @GetMapping("/getCommentatorsByAffiliationId")
    public ResponseEntity<List<Commentator>> getCommentatorsByAffiliationId(@RequestParam Long affiliationId) {
        ResponseEntity<List<Commentator>> ok = ResponseEntity.ok(commentatorService.listCommentatorsByAffiliationId(affiliationId));
        return ok;

    }
     }
