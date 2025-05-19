# Docker & Docker Compose Cheat Sheet

## Docker

| Befehl                                                        | Beschreibung                                 |
|---------------------------------------------------------------|----------------------------------------------|
| `sudo docker logs --follow <container-name>`                  | Container-Logs live anzeigen (prüfen)        |

---

## Docker Compose

| Befehl                                                        | Beschreibung                                                                 |
|---------------------------------------------------------------|-------------------------------------------------------------------------------|
| `docker-compose up -d`                                        | Startet alle Container im Hintergrund (detached mode)                         |
| `docker-compose up --build -d`                                | Erzwingt den Neuaufbau der Images und startet die Container (detached mode)   |
| `docker-compose down`                                         | Stoppt und entfernt alle Container, Netzwerke, Volumes und Images             |
| `docker-compose ps -a`                                        | Zeigt den Status aller Container in der `docker-compose.yml`                  |
| `docker-compose start`                                        | Startet gestoppte Container, ohne sie neu zu erstellen                        |
| `docker-compose stop`                                         | Stoppt alle laufenden Container                                               |
| `docker-compose restart`                                      | Startet alle Container neu                                                    |
| `docker-compose logs`                                         | Zeigt die Logs der laufenden Container                                        |
| `docker-compose logs -f`                                      | Zeigt die Logs im Live-Modus (fortlaufend)                                    |
| `docker-compose build`                                        | Baut oder erneuert die Images, die in der `docker-compose.yml` definiert sind |
| `docker-compose build --no-cache`                             | Baut das Image ohne Verwendung von zwischengespeicherten Schichten            |
| `docker-compose exec <service_name> <command>`                | Führt einen Befehl in einem laufenden Container aus                           |

---

## Kompose

| Befehl                                                        | Beschreibung                                                                 |
|---------------------------------------------------------------|-------------------------------------------------------------------------------|
| `sudo kompose convert -f docker-compose.yml`                  | Wandelt docker-compose in Service-/Deployment-YAMLs für Kubernetes um         |

---