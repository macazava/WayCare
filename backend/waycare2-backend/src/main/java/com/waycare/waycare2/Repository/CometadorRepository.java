package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Commentator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CometadorRepository {
    public interface CommentatorRepository extends JpaRepository<Commentator, Long> {
        List<Commentator> findByReport_Id(Long reportId);
        List<Commentator> findByAffiliation_Id(Long affiliationId);
}
    }

