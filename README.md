# Monster Bounty Server
COSC 4301 – Modern Programming  
Spring 2026  
Author: Nicolas Canales

This project is a Spring Boot REST API for managing creatures and their habitats.  
The application uses PostgreSQL for persistence and Flyway for database migrations.

The project demonstrates:

- Spring Boot REST API development
- PostgreSQL database integration
- Flyway database migrations
- Docker containerization
- DTO and service layer architecture

---

# Project Structure

```
monsterbountyserver
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org/example/monsterbountyserver
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── entity
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── exception
│   │   └── resources
│   │       └── db/migration
├── build.gradle
├── docker-compose.yml
└── gradlew
```

---

# Requirements

You must have the following installed:

- Docker
- Java 17+
- IntelliJ IDEA or another Java IDE

---

# Running the Application

## 1. Start the PostgreSQL database

From inside the `monsterbountyserver` directory run:

```
docker compose up -d
```

This starts a PostgreSQL container with the following configuration:

```
Database: tutorial
Username: tutorial
Password: tutorial
Port: 5436
```

---

## 2. Start the Spring Boot application

Run the application using IntelliJ:

```
MonsterbountyserverApplication.java
```

or using Gradle:

```
./gradlew bootRun
```

When the application starts it will:

- connect to PostgreSQL
- run Flyway migrations
- create the database schema

The API will be available at:

```
http://localhost:8080
```

---

# API Endpoints

## Creatures

Get all creatures

```
GET /api/creatures
```

Get creature by ID

```
GET /api/creatures/{id}
```

Create a creature

```
POST /api/creatures
```

Example request body:

```json
{
  "name": "Hydra",
  "species": "Mythical Beast",
  "dangerLevel": "HIGH",
  "condition": "STABLE",
  "notes": "Example creature",
  "habitatId": 1
}
```

---

## Habitats

Get all habitats

```
GET /api/habitats
```

Get habitat by ID

```
GET /api/habitats/{id}
```

---

# Health Check

```
GET /health
```

Returns application status.

---

# Database Migrations

Database schema is managed using Flyway.

Migration files are located in:

```
src/main/resources/db/migration
```

Flyway runs automatically when the application starts.

---

# Summary

This project implements a REST API using:

- Spring Boot
- PostgreSQL
- Flyway
- Docker
- DTO and service layers

The application provides endpoints for managing creatures and their habitats while demonstrating modern Java backend development practices.