# Submission Deliverables

This page tracks the repository artifacts used for the final academic submission and the repo-specific constraints that affect how reviewers should validate them.

## Key files

- `README.md:1-110` — backend overview, GitHub repository link, runtime prerequisites, local commands, and exposed API summary.
- `documentacao-final-backend.pdf` — original final documentation PDF kept in the repository.
- `documentacao-final-backend-alinhada.pdf` — repo-aligned PDF deliverable with the GitHub link on the cover and corrected backend-only execution instructions.
- `tarefa.md:1-89` — assignment rubric requiring GitHub-hosted source code plus final PDF documentation.

## Current deliverable facts

- The repository link requested for submission is `https://github.com/Carlos-Bianchi/java-challenge-04` and it is present in `README.md` and in `documentacao-final-backend-alinhada.pdf`.
- This workspace is the backend repository root, so local commands are run directly from the root with `./mvnw ...`.
- The aligned PDF removes the earlier assumption of checked-in `frontend/`, Docker Compose, or SQL bootstrap files and instead documents the confirmed requirement: an existing PostgreSQL schema with Quarkus schema generation disabled.
- Maven validation previously confirmed `./mvnw test` and `./mvnw -DskipTests package` succeed in this repository state.

## Gotchas

- The original `documentacao-final-backend.pdf` remains in the repo, but `documentacao-final-backend-alinhada.pdf` is the deliverable aligned to the actual repository layout and instructions.
- The wiki does not treat the PDF as a source of truth for runtime setup; `README.md`, `pom.xml`, and `src/main/resources/application.properties` remain the canonical repo-backed references.

## Backlinks

- [Overview](../overview.md)
- [Build and Configuration](./build-and-configuration.md)
