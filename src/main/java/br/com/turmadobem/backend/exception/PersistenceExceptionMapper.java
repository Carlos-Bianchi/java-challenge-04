package br.com.turmadobem.backend.exception;

import jakarta.persistence.PersistenceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class PersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
    public Response toResponse(PersistenceException exception) {
        return Response.status(Response.Status.CONFLICT)
                .entity(ApiError.of("persistence_conflict", "Operação viola uma restrição do banco de dados"))
                .build();
    }
}
