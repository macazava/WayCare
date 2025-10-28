package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface categoriaRepository {
    public interface CategoriaRepository extends JpaRepository<categoria, Long> {
    }
}
