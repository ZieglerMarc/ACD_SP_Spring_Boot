# Book Rental Microservices System

This repository contains a microservice-based system for managing books, users, and book rentals. The system is built with Spring Boot, uses MariaDB for persistence, and is orchestrated with Docker Compose for easy local development and deployment.

---

## System Overview

The system consists of the following microservices:

- **Book Service**: Manages book data (CRUD operations for books).
- **Book Rental Service**: Handles rental operations (renting and returning books).
- **User Management Service**: Manages user data and user-related operations.
- **Databases**: Each service has its own MariaDB instance for data isolation and microservice independence.

All services communicate over a shared Docker network. The Book Rental Service interacts with the Book Service to update book availability when a book is rented or returned.

---

## Architecture

```
+-------------------+         +---------------------+
|                   |         |                     |
|   User Interface  +-------->+   User Management   +---------+
|                   |         |     Service         |         |
+-------------------+         +---------------------+         |
         |                                                    |
         |                                                    v
         |                                           +---------------------+
         |                                           |                     |
         |                                           |  MariaDB (Users)    |
         |                                           |                     |
         |                                           +---------------------+
         |                                          
         |                                          
         v                                          
+-------------------+         +---------------------+
|                   |         |                     |
| Book Rental       +-------->+     Book Service    +---------+
| Service           |         |                     |         |
+-------------------+         +---------------------+         |
         |                                                    v
         |                                          +---------------------+
         |                                          |                     |
         v                                          |  MariaDB (Books)    |
+---------------------+                             |                     |
|                     |                             +---------------------+
|  MariaDB (Rentals)  |                            
|                     |                            
+---------------------+                          
                                         
```

- Each service has its own database.
- Services communicate via REST APIs using service names as hostnames within Docker Compose.

---

## Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

---

## Build and Run

### 1. Clone the Repository

```sh
git clone https://github.com/ZieglerMarc/ACD_SP_Spring_Boot.git
```

### 2. Build All Services

Each service must be built separately to generate the JAR files for Docker:

```sh
cd book_Service
mvn clean package -DskipTests
cd ../book_Rental_Service
mvn clean package -DskipTests
cd ../Microservice_Boot
mvn clean package -DskipTests
cd ..
```

### 3. Start All Services with Docker Compose

From the project root (where your main `docker-compose.yaml` is located):

```sh
docker-compose up --build
```

This will:
- Build Docker images for all services
- Start all MariaDB containers
- Start all Spring Boot microservices

### 4. Access the APIs

- **Book Service**: [http://localhost:8080](http://localhost:8080)
- **Book Rental Service**: [http://localhost:8081](http://localhost:8080)
- **User Management Service**: [http://localhost:8082](http://localhost:8082)

---

## Configuration

- **Application Properties**: Each service has its own `src/main/resources/application.properties` for configuration.
- **Database Data**: Persisted in the `docker_data/` directories for each service.
- **Ports & Credentials**: Can be adjusted in `docker-compose.yaml` and each service's `application.properties`.

---

## Stopping the Services

To stop all running containers:

```sh
docker-compose down
```

---

## Troubleshooting

- **Database Connection Errors**:  
  If a service cannot connect to its database, ensure the database container is running and healthy. Sometimes, deleting the `docker_data/` directory for a fresh start helps.
- **Port Conflicts**:  
  Make sure the ports defined in `docker-compose.yaml` are not already in use on your host.
- **Service Communication**:  
  Services communicate using their Docker Compose service names (e.g., `bookservice:8080`). Do not use `localhost` for inter-service calls inside Docker.

---

## Development Tips

- To run a service locally (outside Docker), make sure its database is running (either in Docker or locally) and update the `application.properties` accordingly.
- Use `mvn spring-boot:run` for local development.
- For database schema initialization, use the `/docker-entrypoint-initdb.d` feature or Flyway/Liquibase migrations.

---

## Project Structure

```
book_Service/           # Book microservice
book_Rental_Service/    # Book rental microservice
Microservice_Boot/      # User management microservice
docker-compose.yaml     # Main orchestration file
```

---

## Further Reading

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Docker Compose Documentation](https://docs.docker.com/compose/)
- [MariaDB Documentation](https://mariadb.com/kb/en/documentation/)
