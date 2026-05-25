package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.StatusUsuarioRequest;
import br.com.turmadobem.backend.dto.UsuarioRequest;
import br.com.turmadobem.backend.dto.UsuarioResponse;
import br.com.turmadobem.backend.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/usuarios")
public class UsuarioResource {
    private final UsuarioService usuarios;

    public UsuarioResource(UsuarioService usuarios) { this.usuarios = usuarios; }

    @GET
    public Response list() { return Response.ok(usuarios.listAll().stream().map(UsuarioResponse::from).toList()).build(); }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) { return Response.ok(UsuarioResponse.from(usuarios.findById(id))).build(); }

    @POST
    public Response create(@Valid UsuarioRequest request) {
        var usuario = usuarios.registerUser(request);
        return Response.created(URI.create("/api/usuarios/" + usuario.id)).entity(UsuarioResponse.from(usuario)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid UsuarioRequest request) {
        return Response.ok(UsuarioResponse.from(usuarios.update(id, request))).build();
    }

    @PATCH
    @Path("/{id}/status")
    public Response status(@PathParam("id") Long id, @Valid StatusUsuarioRequest request) {
        return Response.ok(UsuarioResponse.from(usuarios.updateStatus(id, request.status()))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarios.delete(id);
        return Response.noContent().build();
    }
}
