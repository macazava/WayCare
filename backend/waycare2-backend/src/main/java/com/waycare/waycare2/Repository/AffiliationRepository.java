package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliationRepository  {
    public interface affiliationRepository extends JpaRepository<Affiliation, Long>{

}
    }
