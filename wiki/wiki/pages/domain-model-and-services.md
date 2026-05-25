# Domain Model and Services

This page summarizes the main backend layers below the HTTP resources.

## Package layout

- `src/main/java/br/com/turmadobem/backend/model/` — domain entities and enums such as `Usuario`, `Paciente`, `DentistaVoluntario`, `Agendamento`, `RegistroAtendimento`, `Comunicacao`, `Match`, and status enums.
- `src/main/java/br/com/turmadobem/backend/service/` — business services including `AuthService`, `UsuarioService`, `MatchService`, `AgendamentoService`, `RegistroAtendimentoService`, `ComunicacaoService`, `DashboardService`, `EspecialidadeService`, and `PasswordService`.
- `src/main/java/br/com/turmadobem/backend/repository/` — repositories for users, patients, dentists, matches, appointments, communications, specialties, addresses, and service records.
- `src/main/java/br/com/turmadobem/backend/dto/` — request and response transport classes used by the REST layer.
- `src/main/java/br/com/turmadobem/backend/exception/` — custom exceptions and exception mappers for API responses.

## Observations

- `src/main/java/br/com/turmadobem/backend/service/PasswordService.java` is covered by a dedicated test file, which suggests password hashing/verification is isolated behind a service boundary.
- The codebase separates resources, services, repositories, DTOs, and models into distinct packages, indicating a conventional layered backend structure.

## Backlinks

- [Overview](../overview.md)
