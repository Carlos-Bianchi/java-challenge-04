# Turma do Bem Backend

Backend API built with Quarkus for the Turma do Bem challenge. The project exposes REST endpoints for authentication, user management, match recommendations, appointments, communications, service records, specialties, and dashboard summaries.

## Stack

- Java 17
- Quarkus 3.15.3
- PostgreSQL
- Hibernate ORM + Hibernate Validator
- OpenAPI via SmallRye
- JUnit 5, Rest Assured, Mockito, AssertJ

## Project structure

```text
src/main/java/br/com/turmadobem/backend/
├── dto/          # Request and response payloads
├── exception/    # API error mapping and custom exceptions
├── model/        # Domain entities and enums
├── repository/   # Persistence access layer
├── resource/     # REST endpoints
└── service/      # Business rules

src/main/resources/
└── application.properties

src/test/java/br/com/turmadobem/backend/service/
└── Service-level tests
```

## Requirements

- JDK 17
- A PostgreSQL instance
- Maven Wrapper included in the repository

## Environment variables

The application reads its runtime configuration from `src/main/resources/application.properties`.

| Variable | Default | Purpose |
| --- | --- | --- |
| `BACKEND_PORT` | `8080` | HTTP port |
| `DATABASE_JDBC_URL` | `jdbc:postgresql://localhost:5432/turma_do_bem` | PostgreSQL JDBC URL |
| `POSTGRES_USER` | `turma_admin` | Database username |
| `POSTGRES_PASSWORD` | `turma_segura_2026` | Database password |
| `CORS_ORIGINS` | `*` | Allowed CORS origins |

## Running locally

Start the application in dev mode:

```bash
./mvnw quarkus:dev
```

The API will be available at `http://localhost:8080` unless `BACKEND_PORT` is overridden.

## Build and test

Run the automated tests:

```bash
./mvnw test
```

Create the application package:

```bash
./mvnw package
```

## API overview

Main resources currently exposed by the backend:

- `POST /api/auth/login`
- `POST /api/auth/register`
- `/api/usuarios`: list, get by id, create, update by id, update status by id, delete by id
- `/api/agendamentos`: list, get by id, create, update by id, delete by id
- `/api/registros-atendimento`: list, get by id, create, update by id, delete by id
- `/api/comunicacoes`: list, get by id, create, mark as read by id, delete by id
- `/api/especialidades`: list
- `/api/matches`: list, get by id, create, delete by id, recommendation lookup, status update by id
- `GET /api/matches/recommendations?patientId={id}`
- `PATCH /api/matches/{id}/status`
- `GET /api/dashboard/summary`

The OpenAPI document is exposed at:

```text
/q/openapi
```

## Tests currently present

The repository already includes service-level tests for:

- `MatchServiceTest`
- `UsuarioServiceTest`
- `RegistroAtendimentoServiceTest`
- `PasswordServiceTest`

## Notes

- The project artifact is named `challenge-backend` in `pom.xml`.
- Database schema generation is configured as `none`, so the application expects an existing PostgreSQL schema.
