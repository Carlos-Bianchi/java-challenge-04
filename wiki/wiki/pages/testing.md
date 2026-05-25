# Testing

This page summarizes the automated tests currently present in the repository.

## Test files

- `src/test/java/br/com/turmadobem/backend/service/MatchServiceTest.java`
- `src/test/java/br/com/turmadobem/backend/service/UsuarioServiceTest.java`
- `src/test/java/br/com/turmadobem/backend/service/RegistroAtendimentoServiceTest.java`
- `src/test/java/br/com/turmadobem/backend/service/PasswordServiceTest.java`

## Notes

- The current visible test suite is focused on service-layer behavior.
- Test dependencies declared in `pom.xml:62-83` include JUnit 5, Rest Assured, Mockito, and AssertJ.

## Backlinks

- [Overview](../overview.md)
