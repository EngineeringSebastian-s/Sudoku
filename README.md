# Sudoku

Este proyecto es una **aplicación web de Sudoku** desarrollada con **Spring Boot** y **Thymeleaf**.  

Incluye:  
- Controladores MVC  
- Servicio de Sudoku con backtracking  
- Exposición vía RMI  
- Vistas dinámicas con HTML + CSS  
- Configuración con `application.yml`  

---

## Arquitectura General

El siguiente diagrama muestra la arquitectura de la aplicación, incluyendo frontend, backend, recursos estáticos y cliente externo vía RMI:

```mermaid
flowchart TD
    %% Clients
    Browser["Web Browser"]:::frontend
    ExtClient["External RMI Client"]:::external

    %% Backend System
    subgraph "Backend (Spring Boot Server)"
        direction TB

        subgraph "Controllers Layer"
            direction TB
            MenuCtrl["MenuController"]:::controller
            SudokuCtrl["SudokuController"]:::controller
        end

        subgraph "Service Layer"
            direction TB
            ISvc["ISudokuService <<interface>>"]:::service
            SvcImp["SudokuService"]:::service
        end

        subgraph "Configuration"
            direction TB
            AppYml["application.yml"]:::config
            RmiCfg["RmiServerConfig"]:::config
        end

        subgraph "Resources"
            direction TB
            Templates["Templates<br/>(menu.html, board.html)"]:::resource
            StaticRes["Static CSS<br/>(board.css, main.css,<br/>menu.css, root.css)"]:::resource
        end
    end

    %% Interactions
    Browser -->|"HTTP GET/POST"| MenuCtrl
    Browser -->|"HTTP GET/POST"| SudokuCtrl
    MenuCtrl -->|"calls"| ISvc
    SudokuCtrl -->|"calls"| ISvc
    ISvc -->|"implemented by"| SvcImp
    MenuCtrl -->|"returns view"| Templates
    SudokuCtrl -->|"returns view"| Templates
    Browser -->|"requests CSS"| StaticRes

    ExtClient -->|"RMI call"| RmiCfg
    RmiCfg -->|"exposes methods of"| SvcImp

    %% Styles
    classDef frontend fill:#B3DDF2,stroke:#333,stroke-width:1px
    classDef external fill:#F2D7D5,stroke:#333,stroke-width:1px
    classDef controller fill:#FDE9D9,stroke:#333,stroke-width:1px
    classDef service fill:#E2F0D9,stroke:#333,stroke-width:1px
    classDef config fill:#D9EAF7,stroke:#333,stroke-width:1px
    classDef resource fill:#FFF2CC,stroke:#333,stroke-width:1px
```


## Diagrama de clases UML
Este diagrama representa las clases Java principales, sus métodos y relaciones:

```mermaid
classDiagram
    class MenuController {
        +showMenu() String
    }

    class SudokuController {
        +getBoard(size : int) ModelAndView
        +solveBoard(size : int) ModelAndView
    }

    class ISudokuService {
        <<interface>>
        +generateEmptyBoard(size : int) int[][]
        +generateSolvedBoard(size : int) int[][]
        +getSegmentDimensions(size : int) int[]
        +isValidMove(board : int[][], row : int, col : int, num : int) boolean
    }

    class SudokuService {
        +generateEmptyBoard(size : int) int[][]
        +generateSolvedBoard(size : int) int[][]
        +getSegmentDimensions(size : int) int[]
        +isValidMove(board : int[][], row : int, col : int, num : int) boolean
        -backtrackingAlgorithm()
    }

    class RmiServerConfig {
        -port : int = 1099
        +rmiServiceExporter(service : ISudokuService) RmiServiceExporter
    }

    %% Relations
    MenuController --> ISudokuService : uses
    SudokuController --> ISudokuService : uses
    SudokuService ..|> ISudokuService : implements
    RmiServerConfig --> SudokuService : exposes
```
