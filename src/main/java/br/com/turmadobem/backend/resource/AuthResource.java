package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.LoginRequest;
import br.com.turmadobem.backend.dto.UsuarioRequest;
import br.com.turmadobem.backend.dto.UsuarioResponse;
import br.com.turmadobem.backend.service.AuthService;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/auth")
public class AuthResource {
    private final AuthService auth;

    public AuthResource(AuthService auth) {
        this.auth = auth;
    }

    @POST
    @Path("/login")
    public Response login(@Valid LoginRequest request) {
        return Response.ok(auth.login(request)).build();
    }

    @POST
    @Path("/register")
    public Response register(@Valid UsuarioRequest request) {
        var usuario = auth.register(request);
        return Response.created(URI.create("/api/usuarios/" + usuario.id)).entity(UsuarioResponse.from(usuario)).build();
    }
}
