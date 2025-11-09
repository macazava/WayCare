package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Utilizador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Waycareaplication {
    @com.waycare.waycare2.Repository.SpringBootApplication
    public class WaycareApplication {

        public static void main(String[] args) {
            SpringApplication.run(WaycareApplication.class, args);
        }

    }

    interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    }
}
