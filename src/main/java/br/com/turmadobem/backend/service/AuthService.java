package br.com.turmadobem.backend.service;

import br.com.turmadobem.backend.dto.AuthResponse;
import br.com.turmadobem.backend.dto.LoginRequest;
import br.com.turmadobem.backend.dto.UsuarioRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.model.Usuario;
import br.com.turmadobem.backend.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class AuthService {
    private final UsuarioRepository usuarios;
    private final UsuarioService usuarioService;
    private final PasswordService passwords;

    public AuthService(UsuarioRepository usuarios, UsuarioService usuarioService, PasswordService passwords) {
        this.usuarios = usuarios;
        this.usuarioService = usuarioService;
        this.passwords = passwords;
    }

    public AuthResponse login(LoginRequest request) {
        Usuario usuario = usuarios.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(Response.Status.UNAUTHORIZED, "invalid_credentials", "Credenciais inválidas"));
        if (!passwords.verify(request.senha(), usuario.senhaHash)) {
            throw new BusinessException(Response.Status.UNAUTHORIZED, "invalid_credentials", "Credenciais inválidas");
        }
        return new AuthResponse(usuario.id, usuario.nomeCompleto, usuario.email, usuario.papel.name(), "sessionless");
    }

    public Usuario register(UsuarioRequest request) {
        return usuarioService.registerUser(request);
    }
}
