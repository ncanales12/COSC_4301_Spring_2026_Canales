This project implements a REST API for managing creatures and habitats using Spring Boot, PostgreSQL, Flyway migrations, and Docker.

# Monster Bounty Server

A Spring Boot REST API for tracking creatures and habitats in the fictional Neon Ark ecosystem.

This project demonstrates modern backend development practices including:

- Spring Boot REST APIs
- PostgreSQL database integration
- JPA/Hibernate ORM
- Flyway database migrations
- Dockerized database environment
- Centralized exception handling
- RESTful API design

---

# Tech Stack

Java 17  
Spring Boot  
Spring Data JPA  
PostgreSQL  
Flyway  
Docker

---

# Running the Project

## 1. Start Docker

Make sure Docker Desktop is running.

Start the PostgreSQL database container:

docker compose up -d
---

## 2. Start the Spring Boot Application

Run the application using Gradle:

docker compose up -d

The API will start on:
http://localhost:8080


---

# API Endpoints

## Creatures

| Method | Endpoint | Description |
|------|------|------|
| GET | `/api/creatures` | Get all creatures |
| GET | `/api/creatures/{id}` | Get creature by ID |
| POST | `/api/creatures` | Create creature |
| PUT | `/api/creatures/{id}` | Update creature |
| DELETE | `/api/creatures/{id}` | Delete creature |

---

## Habitats

| Method | Endpoint | Description |
|------|------|------|
| GET | `/api/habitats` | Get all habitats |
| GET | `/api/habitats/{id}` | Get habitat by ID |

---

# Example Creature JSON

```json
{
  "name": "Goblin King",
  "species": "Monster",
  "dangerLevel": "HIGH",
  "condition": "CRITICAL",
  "notes": "Test creature"
}

## Example API Calls

Get creatures:

curl http://localhost:8080/api/creatures

Create creature:

curl -X POST http://localhost:8080/api/creatures \
-H "Content-Type: application/json" \
-d '{"name":"Fang","species":"Dire Wolf","dangerLevel":"HIGH"}'

## Health Check

Verify the API is running:

curl http://localhost:8080/health