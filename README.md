# Web Quiz Engine

[![en](https://img.shields.io/badge/lang-en-red.svg)](README.md)

## Introduction

A Spring Boot-based REST API that enables users to register, create quizzes, submit answers, and track quiz completions. Designed as the backend for quiz-based learning apps or coding challenge platforms.

## Features

- ✅ User registration with secure password hashing
- 🧠 Create, retrieve, solve, and delete quizzes
- 📋 Track correctly completed quizzes by authenticated users
- 📄 Pagination support for efficient data browsing
- 🛡️ Robust error handling and exception management
- 🔒 Stateless, HTTP Basic-auth protected endpoints

## API Endpoints

| Method | Endpoint                   | Description                                 |
|--------|----------------------------|---------------------------------------------|
| POST   | `/api/register`            | Register a new user                         |
| GET    | `/api/quizzes`             | Get all quizzes (paginated)                 |
| POST   | `/api/quizzes`             | Create a new quiz                           |
| GET    | `/api/quizzes/{id}`        | Get quiz by ID                              |
| DELETE | `/api/quizzes/{id}`        | Delete quiz (if owner)                      |
| POST   | `/api/quizzes/{id}/solve`  | Submit answer for a quiz                    |
| GET    | `/api/quizzes/completed`   | Get user's successfully completed quizzes   |

## Technologies

- **Spring Boot 3**
- **Spring Security**
- **Spring Data JPA**
- **H2 Database** (file-based)
- **Lombok**
- **Jakarta Validation**

## Project Structure

```
Web Quiz Engine with Java/
└── task/
    └── src/
        └── engine/
            ├── controller
            ├── exception
            ├── model
            │   ├── request
            │   └── response
            ├── repository
            ├── service
            └── WebQuizEngine.java
```

> ⚠️ Note: All production source code resides in `Web Quiz Engine with Java/task/src/`.  
> Other folders are used for practice, experimentation, or staging features during development.

## Getting Started

1. Clone the repository:
   ```bash
   git clone <your-repo-url>
   cd "Web Quiz Engine with Java/task"
   ```

2. Build and run using **Gradle**:
   ```bash
   ./gradlew bootRun
   ```

3. Access API via tools like Postman or curl.

## Authentication

- Basic Auth using email and password
- Protected endpoints require valid credentials
- Stateless session management for scalability

## Notes

- H2 Console (if enabled): `/h2-console`
- **Database is persistent** across restarts using file-based storage:
  ```properties
  spring.datasource.url=jdbc:h2:file:../quizdb
  ```

