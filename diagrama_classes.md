# Esboço do Diagrama de Classes UML

O diagrama abaixo representa a estrutura conceptual do sistema **WayCare**, evidenciando as principais classes e as relações entre elas.

```mermaid
classDiagram
    class Utilizador {
        +id: Long
        +nome: String
        +email: String
    }

    class Reporte {
        +id: Long
        +descricao: String
        +data: Date
        +foto: String
    }

    class Comentario {
        +id: Long
        +texto: String
        +data: Date
    }

    class Categoria {
        +id: Long
        +nome: String
        +descricao: String
    }

    class Obstaculo {
        +id: Long
        +localizacao: String
        +tipo: String
    }

    class Notificacao {
        +id: Long
        +mensagem: String
        +dataEnvio: Date
    }

    %% RELAÇÕES (iguais às do teu diagrama desenhado)

    Utilizador "1" --> "0..*" Reporte : cria
    Utilizador "1" --> "0..*" Comentario : escreve
    Reporte "1" --> "1" Obstaculo : descreve
    Reporte "1" --> "0..*" Comentario : recebe
    Reporte "1" --> "0..*" Notificacao : gera
    Obstaculo "1" --> "1" Categoria : pertence
