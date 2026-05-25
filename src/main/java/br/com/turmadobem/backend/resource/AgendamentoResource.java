package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.AgendamentoRequest;
import br.com.turmadobem.backend.dto.AgendamentoResponse;
import br.com.turmadobem.backend.service.AgendamentoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/agendamentos")
public class AgendamentoResource {
    private final AgendamentoService agendamentos;

    public AgendamentoResource(AgendamentoService agendamentos) { this.agendamentos = agendamentos; }

    @GET
    public Response list() { return Response.ok(agendamentos.listAll().stream().map(AgendamentoResponse::from).toList()).build(); }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) { return Response.ok(AgendamentoResponse.from(agendamentos.findById(id))).build(); }

    @POST
    public Response create(@Valid AgendamentoRequest request) {
        var agendamento = agendamentos.create(request);
        return Response.created(URI.create("/api/agendamentos/" + agendamento.id)).entity(AgendamentoResponse.from(agendamento)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid AgendamentoRequest request) {
        return Response.ok(AgendamentoResponse.from(agendamentos.update(id, request))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        agendamentos.delete(id);
        return Response.noContent().build();
    }
}
