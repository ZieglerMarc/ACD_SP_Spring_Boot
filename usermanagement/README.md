# Book Service

This is a Spring Boot microservice for managing users, designed to run with a MariaDB database using Docker Compose.

## Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose

## Build and Run

### 1. Build the Application with Maven

To build the application and skip tests, run:

```sh
mvn clean package -DskipTests
```

### 2. Start the Application with Docker Compose

To build and start all services (including the database) in detached mode, use:


```sh
docker-compose up --build -d
```

### 3. Start the Application without Docker in JVM locally

To start application with maven command locally

```sh
mvn spring-boot:run
```


This will:
- Build the Docker image for the Book Service
- Start the MariaDB database
- Start the Book Service application

## Stopping the Services

To stop all running containers, use:

```sh
docker-compose down
```

## Configuration

- The application configuration can be found in `src/main/resources/application.properties`.
- Database data is persisted in the `docker_data/` directory.
- You can adjust ports and credentials in `docker-compose.yaml` and `application.properties`.

## Access

- The Book Service API will be available at [http://localhost:8080](http://localhost:8080) by default.


```
