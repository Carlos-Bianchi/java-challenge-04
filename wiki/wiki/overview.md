# Project Overview

This repository is a Quarkus-based Java 17 backend for the Turma do Bem challenge, centered on a PostgreSQL-backed REST API for authentication, user management, appointments, clinical service records, dentist-patient matching, communications, specialties, and dashboard summaries.

## Modules

- **Build and runtime config** — Maven and Quarkus configuration live in [`pom.xml`](../../pom.xml) and [`src/main/resources/application.properties`](../../src/main/resources/application.properties). See [Build and Configuration](./pages/build-and-configuration.md).
- **HTTP API layer** — REST endpoints are implemented under `src/main/java/br/com/turmadobem/backend/resource/`. See [API Resources](./pages/api-resources.md).
- **Domain and persistence layer** — entities, repositories, DTOs, and services live under `model/`, `repository/`, `dto/`, and `service/`. See [Domain Model and Services](./pages/domain-model-and-services.md).
- **Automated tests** — service-focused tests live in `src/test/java/br/com/turmadobem/backend/service/`. See [Testing](./pages/testing.md).
