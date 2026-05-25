package br.com.turmadobem.backend.dto;

public record AuthResponse(Long userId, String nomeCompleto, String email, String papel, String tokenType) {
}
