# Esboço do Diagrama de Classes UML

O diagrama abaixo representa a estrutura conceptual do sistema **WayCare**, evidenciando as principais classes e as relações entre elas.

```mermaid
classDiagram
direction LR

    class Utilizador {}
    class Reporte {}
    class Comentario {}
    class Obstaculo {}
    class Categoria {}
    class Notificacao {}

    %% Relações principais (iguais ao teu diagrama)
    Utilizador "1" --> "0..*" Reporte : cria
    Utilizador "1" --> "0..*" Comentario : escreve
    Reporte "1" --> "1" Obstaculo : descreve
    Reporte "1" --> "0..*" Comentario : recebe
    Reporte "1" --> "0..*" Notificacao : gera
    Obstaculo "1" --> "1" Categoria : pertence

