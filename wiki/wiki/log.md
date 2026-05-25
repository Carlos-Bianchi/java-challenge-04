# Wiki Change Log

- 2026-05-24 — Initialized wiki structure and added baseline pages for build/configuration, API resources, domain/services, and tests.
- 2026-05-24 — Added `README.md` documenting local setup, environment variables, commands, and exposed API areas.
- 2026-05-24 — Tightened API wording in `README.md` and corrected the communications read method in `wiki/wiki/pages/api-resources.md`.
- 2026-05-24 — Updated `wiki/wiki/pages/build-and-configuration.md`: documented that runtime commands are run from the backend repo root and that no Compose/SQL setup assets are checked into this repository.
- 2026-05-24 — Updated `wiki/wiki/overview.md`: added a submission-artifacts module entry linking to the repo-aligned deliverables page.
- 2026-05-24 — Created `wiki/wiki/pages/submission-deliverables.md`: tracked `README.md`, `documentacao-final-backend-alinhada.pdf`, the GitHub repository link, and the backend-only submission constraints.
- 2026-05-24 — Prepared Docker-based Render deployment: added `Dockerfile`, `.dockerignore`, and `render.yaml`; updated `application.properties` for Render host/port; documented the deployment tutorial in `README.md`; refreshed wiki build/config and overview pages.
- 2026-05-25 — Fixed the Render Docker build path: removed the hidden `.mvn/` dependency from `Dockerfile` and switched the build stage to a Maven base image so Render can build from the GitHub checkout alone.
