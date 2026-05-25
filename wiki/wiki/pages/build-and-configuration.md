# Build and Configuration

This page summarizes how the backend is built and configured at runtime.

## Key files

- `pom.xml:4-18` — project coordinates, Java 17 target, Quarkus BOM, and plugin versions.
- `pom.xml:32-84` — runtime and test dependencies including Quarkus REST, Jackson, Hibernate ORM, PostgreSQL, validator, OpenAPI, BCrypt, JUnit 5, Rest Assured, Mockito, and AssertJ.
- `pom.xml:86-140` — Quarkus Maven plugin, compiler plugin, Surefire, and Failsafe setup.
- `src/main/resources/application.properties:1-20` — application name, Render-compatible HTTP host/port, datasource, CORS, and OpenAPI path.
- `Dockerfile:1-16` — multi-stage Java 17 container build for Quarkus fast-jar packaging.
- `render.yaml:1-17` — Render Blueprint for a Docker web service with env var placeholders.
- `.dockerignore:1-14` — Docker build-context exclusions for local and documentation artifacts.

## Runtime configuration

Environment-backed properties currently defined:

| Property | Default |
| --- | --- |
| `quarkus.http.host` | `0.0.0.0` |
| `quarkus.http.port` | `8080` via `PORT` and fallback `BACKEND_PORT` |
| `quarkus.datasource.jdbc.url` | `jdbc:postgresql://localhost:5432/turma_do_bem` |
| `quarkus.datasource.username` | `turma_admin` |
| `quarkus.datasource.password` | `turma_segura_2026` |
| `quarkus.http.cors.origins` | `*` |

## Operational notes

- `quarkus.hibernate-orm.database.generation=none` in `src/main/resources/application.properties:9` means the database schema must already exist.
- OpenAPI is exposed at `src/main/resources/application.properties:17` on `/q/openapi`.
- Runtime commands are executed from the repository root; this workspace is the backend submission itself, not a monorepo with nested `backend/` and `frontend/` directories.
- The repo does not include `docker-compose*.yml` or `.sql` setup files, so local execution depends on an existing reachable PostgreSQL instance rather than checked-in Compose/schema assets.
- Render deployment now follows the Docker-based path documented in `README.md`, with Quarkus listening on `0.0.0.0` and honoring Render's injected `PORT` variable.

## Backlinks

- [Overview](../overview.md)
- [Submission Deliverables](./submission-deliverables.md)
