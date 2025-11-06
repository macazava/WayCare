# Esboço do Diagrama de Classes UML

O diagrama abaixo representa a estrutura conceptual do sistema **WayCare**, evidenciando as principais classes e as relações lógicas da base de dados WayCare,



---

## Diagrama Entidade-Relacão (ER)

```mermaid
erDiagram
    UTILIZADOR {
    }

    TIPO_ANOMALIA {
    }

    ANOMALIA {
    }

    LOCALIZACAO {
    }

    REPORTE {
    }

    FOTOGRAFIA {
    }

    %% Relações
    UTILIZADOR ||--o{ REPORTE : "1:N (utilizador pode criar vários reportes)"
    TIPO_ANOMALIA ||--o{ ANOMALIA : "1:N (um tipo pode ter várias anomalias)"
    ANOMALIA ||--o{ REPORTE : "1:N (uma anomalia pode ser reportada várias vezes)"
    LOCALIZACAO ||--o{ REPORTE : "1:N (vários reportes podem referir a mesma localização)"
    REPORTE ||--o{ FOTOGRAFIA : "1:N (um reporte pode ter várias fotografias)"
