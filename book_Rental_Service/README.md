# Book Service

This is a Spring Boot microservice for managing (book)rentals, designed to run with a MariaDB database using Docker Compose.

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

### 2. Start the Application without Docker in JVM locally

To start application with maven command locally

```sh
mvn spring-boot:run
```


## Configuration

- The application configuration can be found in `src/main/resources/application.properties`.
- Database data is persisted in the `docker_data/` directory.
- You can adjust ports and credentials in `docker-compose.yaml` and `application.properties`.

## Access

- The Book Rental Service API will be available at [http://localhost:8080](http://localhost:8080) by default.


```
