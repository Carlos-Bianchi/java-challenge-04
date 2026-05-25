package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.RegistroAtendimentoRequest;
import br.com.turmadobem.backend.dto.RegistroAtendimentoResponse;
import br.com.turmadobem.backend.service.RegistroAtendimentoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/registros-atendimento")
public class RegistroAtendimentoResource {
    private final RegistroAtendimentoService registros;

    public RegistroAtendimentoResource(RegistroAtendimentoService registros) { this.registros = registros; }

    @GET
    public Response list() { return Response.ok(registros.listAll().stream().map(RegistroAtendimentoResponse::from).toList()).build(); }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) { return Response.ok(RegistroAtendimentoResponse.from(registros.findById(id))).build(); }

    @POST
    public Response create(@Valid RegistroAtendimentoRequest request) {
        var registro = registros.recordAttendance(request);
        return Response.created(URI.create("/api/registros-atendimento/" + registro.id)).entity(RegistroAtendimentoResponse.from(registro)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid RegistroAtendimentoRequest request) {
        return Response.ok(RegistroAtendimentoResponse.from(registros.update(id, request))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        registros.delete(id);
        return Response.noContent().build();
    }
}
