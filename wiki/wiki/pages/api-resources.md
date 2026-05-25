# API Resources

This page summarizes the REST resources currently exposed by the backend.

## Key files

- `src/main/java/br/com/turmadobem/backend/resource/AuthResource.java:13-32` — `/api/auth` with login and registration endpoints.
- `src/main/java/br/com/turmadobem/backend/resource/UsuarioResource.java:18-54` — `/api/usuarios` CRUD plus status update.
- `src/main/java/br/com/turmadobem/backend/resource/MatchResource.java:19-58` — `/api/matches` CRUD, status updates, and recommendation lookup.
- `[UNVERIFIED]` `src/main/java/br/com/turmadobem/backend/resource/AgendamentoResource.java` — `/api/agendamentos` endpoints observed via annotation scan.
- `[UNVERIFIED]` `src/main/java/br/com/turmadobem/backend/resource/RegistroAtendimentoResource.java` — `/api/registros-atendimento` endpoints observed via annotation scan.
- `[UNVERIFIED]` `src/main/java/br/com/turmadobem/backend/resource/ComunicacaoResource.java` — `/api/comunicacoes` endpoints observed via annotation scan.
- `[UNVERIFIED]` `src/main/java/br/com/turmadobem/backend/resource/EspecialidadeResource.java` — `/api/especialidades` GET endpoint observed via annotation scan.
- `[UNVERIFIED]` `src/main/java/br/com/turmadobem/backend/resource/DashboardResource.java` — `/api/dashboard/summary` GET endpoint observed via annotation scan.

## Endpoint map

| Base path | Methods seen |
| --- | --- |
| `/api/auth` | `POST /login`, `POST /register` |
| `/api/usuarios` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `PATCH /{id}/status`, `DELETE /{id}` |
| `/api/agendamentos` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/api/registros-atendimento` | `GET`, `GET /{id}`, `POST`, `PUT /{id}`, `DELETE /{id}` |
| `/api/comunicacoes` | `GET`, `GET /{id}`, `POST`, `PATCH /{id}/read`, `DELETE /{id}` |
| `/api/especialidades` | `GET` |
| `/api/matches` | `GET`, `GET /{id}`, `GET /recommendations`, `POST`, `PATCH /{id}/status`, `DELETE /{id}` |
| `/api/dashboard` | `GET /summary` |

## Backlinks

- [Overview](../overview.md)
