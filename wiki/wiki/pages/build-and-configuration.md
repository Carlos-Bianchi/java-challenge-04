# Build and Configuration

This page summarizes how the backend is built and configured at runtime.

## Key files

- `pom.xml:4-18` — project coordinates, Java 17 target, Quarkus BOM, and plugin versions.
- `pom.xml:32-84` — runtime and test dependencies including Quarkus REST, Jackson, Hibernate ORM, PostgreSQL, validator, OpenAPI, BCrypt, JUnit 5, Rest Assured, Mockito, and AssertJ.
- `pom.xml:86-140` — Quarkus Maven plugin, compiler plugin, Surefire, and Failsafe setup.
- `src/main/resources/application.properties:1-19` — application name, HTTP port, datasource, CORS, and OpenAPI path.

## Runtime configuration

Environment-backed properties currently defined:

| Property | Default |
| --- | --- |
| `quarkus.http.port` | `8080` via `BACKEND_PORT` |
| `quarkus.datasource.jdbc.url` | `jdbc:postgresql://localhost:5432/turma_do_bem` |
| `quarkus.datasource.username` | `turma_admin` |
| `quarkus.datasource.password` | `turma_segura_2026` |
| `quarkus.http.cors.origins` | `*` |

## Operational notes

- `quarkus.hibernate-orm.database.generation=none` in `src/main/resources/application.properties:9` means the database schema must already exist.
- OpenAPI is exposed at `src/main/resources/application.properties:17` on `/q/openapi`.

## Backlinks

- [Overview](../overview.md)
