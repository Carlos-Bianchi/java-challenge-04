package br.com.turmadobem.backend.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {
    public Response toResponse(BusinessException exception) {
        return Response.status(exception.status()).entity(ApiError.of(exception.code(), exception.getMessage())).build();
    }
}
