package br.com.turmadobem.backend.exception;

import jakarta.ws.rs.core.Response;

public class NotFoundException extends BusinessException {
    public NotFoundException(String resource, Long id) {
        super(Response.Status.NOT_FOUND, "not_found", resource + " não encontrado: " + id);
    }
}
