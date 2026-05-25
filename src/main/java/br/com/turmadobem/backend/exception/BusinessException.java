package br.com.turmadobem.backend.exception;

import jakarta.ws.rs.core.Response;

public class BusinessException extends RuntimeException {
    private final Response.Status status;
    private final String code;

    public BusinessException(Response.Status status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public Response.Status status() {
        return status;
    }

    public String code() {
        return code;
    }
}
