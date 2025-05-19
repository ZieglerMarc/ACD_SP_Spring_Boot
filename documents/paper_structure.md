# Wissenschaftliche Ausarbeitung: Microservices-Architektur mit Java, Spring Boot, Hibernate und Docker

## 0. Inhaltsverzeichnis

1. [Kurze Einleitung zum Thema](#1-kurze-einleitung-zum-thema)
    - Motivation und Ziel der Arbeit
    - Problemstellung und Relevanz von Microservice-Architekturen
    - Überblick über die Struktur des Papers
    - Ziele der Analyse (Erkenntnisgewinnung)

2. [Grundlagen](#2-grundlagen)
    - [Microservices](#21-microservices)
        - Definition und Merkmale
        - Abgrenzung zu monolithischen Architekturen
        - Vorteile
        - Herausforderungen und Nachteile
    - [Containerisierung und Orchestrierung](#22-containerisierung-und-orchestrierung)
        - [Nutzung von Docker und Docker Compose](#221-nutzung-von-docker-und-docker-compose)
            - Containerisierungskonzepte
            - Aufbau und Vorteile des Docker Compose Netzwerks
            - Vergleich: Docker für Microservices vs. lokal oder VM
            - Nachteile und Grenzen
    - [Technologieüberblick](#23-technologieüberblick)
        - [Java und Spring Boot](#231-java-und-spring-boot)
            - Kernkonzepte von Spring Boot
            - Vorteile und Nutzen
            - Limitierungen und Herausforderungen
        - [Hibernate und ORM](#232-hibernate-und-orm)
            - Grundlagen von ORM
            - Hibernate Features und Vorteile
            - Herausforderungen und Nachteile (z.B. Performanceprobleme)
        - [Datenbanken und Zugriff](#233-datenbanken)
            - Relationale Datenbanken (Generelle Hinweise)
                - Konzepte, Architektur und Vorteile
                - Nachteile und Grenzen
            - Nicht-relationale Datenbanken (Kurzüberblick)
                - Konzepte, Anwendungsfälle und Vorteile
                - Nachteile und Grenzen
            - JPA (Java Persistence API)
                - Rolle in der Java-Umgebung
                - Vorteile der Nutzung von JPA
                - Herausforderungen und Komplexität
            - JDBC (Java Database Connectivity)
                - Grundkonzepte und Funktionalität
                - Vorzüge von direktem Datenbankzugriff
                - Nachteile und Anwendungsgrenzen
            - H2-Datenbank
                - Einsatzmöglichkeiten als In-Memory-Datenbank
                - Vorteile für Entwicklung und Testing
                - Einschränkungen im Produktiveinsatz
            - [MariaDB](#2331-mariadb)
                - Architektur und Konzepte
                - Integration mit Spring Boot und Hibernate
                - Vorteile von MariaDB
                    - Open Source und MySQL-Kompatibilität
                    - Performance und Skalierbarkeit
                - Herausforderungen und Nachteile
                    - Feature-Vergleich zu anderen RDBMS
                    - Skalierungsgrenzen und Performanceprobleme               
            - Auswahlkriterien für Datenbanken

3. [Konzeption des Prototyps](#3-konzeption-des-prototyps)
    - [Zielsetzung und Anforderungen](#31-zielsetzung-und-anforderungen)
        - Funktionale Anforderungen
        - Nicht-funktionale Anforderungen
    - [Architekturübersicht](#32-architekturübersicht)
        - UML-Diagramme (erstellen und beschreiben)
        - Beschreibung der drei Microservices
        - Kommunikationswege und API-Schnittstellen
        - Nutzung von docker-compose zur Koordination
    - [Datenmodell und Persistenz](#33-datenmodell-und-persistenz)
        - Datenstruktur und Beziehungen
        - Einsatz von Hibernate zur Abbildung

4. [Implementierung](#4-implementierung)
    - [Technologiestack im Detail](#41-technologiestack-im-detail)
        - Aufbau eines Spring Boot Microservices
        - Beispielhafte Codeausschnitte
        - Integration von Hibernate und JPA
            - Entitäts-Mapping und Objekt-Relationales Modell
            - Strategien zur Performanceoptimierung
            - Fallstricke und deren Bewältigung
        - Anbindung an MariaDB über JDBC
            - Auswahl und Einsatz von spezifischen Datenbanklösungen
            - Integration und Transaktionsmanagement
            - Herausforderungen bei der Datenbankmigration
    - [Containerisierung mit Docker](#42-containerisierung-mit-docker)
        - Infrastructure as Code (IaC)
        - Erstellung und Struktur der Dockerfiles
        - docker-compose.yml Aufbau und Funktionen      

5. [Bewertung der eingesetzten Technologien](#5-bewertung-der-eingesetzten-technologien)
    - [Spring Boot](#51-spring-boot)
        - Kritische Analyse von Vorteilen (Entwicklungsproduktivität, Community-Support)
        - Kritische Analyse von Nachteilen (Overhead, Flexibilität)
    - [Hibernate](#52-hibernate)
        - Kritische Analyse von Vorteilen (Persistenz, DB-Abstraktion)
        - Kritische Analyse von Nachteilen (Performanceprobleme, versteckte Queries)
    - [MariaDB](#53-mariadb)
        - Kritische Analyse von Vorteilen (Open Source, Performance)
        - Kritische Analyse von Nachteilen (Feature-Vergleich, Skalierungsgrenzen)

6. [Alternativen zu den verwendeten Technologien](#6-alternativen-zu-den-verwendeten-technologien)
    - [Alternativen zu Spring Boot](#61-alternativen-zu-spring-boot)
        - Gibt es Alternativen im Java umfeld? Vielleicht lieber dieser als Vergleich nehmen
        - Micronaut, Quarkus, Node.js mit Express: Vor- und Nachteile
    - [Alternativen zu Hibernate](#62-alternativen-zu-hibernate)
        - MyBatis, JDBC direkt, JOOQ: Vor- und Nachteile
    - [Alternativen zu MariaDB](#63-alternativen-zu-mariadb)
        - PostgreSQL, MongoDB (NoSQL), SQLite (embedded): Vor- und Nachteile

7. [Evaluation und Diskussion](#7-evaluation-und-diskussion)
    - [Analyse der Vorteile der gewählten Technologien](#71-analyse-der-vorteile-der-gewählten-technologien)
        - Effizienz und Performance
        - Skalierbarkeit und Flexibilität
        - Entwicklungsproduktivität
    - [Herausforderungen und Probleme](#72-herausforderungen-und-probleme)
        - Technische Komplexität
        - Ressourcenverbrauch
        - Wartung und Weiterentwicklung
    - [Verbesserungsvorschläge](#73-verbesserungsvorschläge)
        - Potentielle Optimierungen
        - Zukünftige Forschungs- und Entwicklungsfelder

8. [Fazit und Ausblick](#8-fazit-und-ausblick)
    - Zusammenfassung der Erkenntnisse
    - Bewertung des Gesamtansatzes (technisch und methodisch)
    - Potenzial für Weiterentwicklung (z.B. Kubernetes, CI/CD, Monitoring)

9. [Literaturverzeichnis](#9-literaturverzeichnis)

10. [Anhang](#10-anhang)
    - Quellcode-Ausschnitte
    - docker-compose.yml
    - UML-Diagramme (falls vorhanden)
    - Projektstruktur
    - Link zum Repo oder Link zur Clousi?
