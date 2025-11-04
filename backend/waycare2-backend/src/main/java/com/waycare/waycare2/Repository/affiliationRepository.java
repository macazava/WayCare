package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface affiliationRepository {
    public interface AffiliationRepository extends JpaRepository<Affiliation, Long>{

}
    }
