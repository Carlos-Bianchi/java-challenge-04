package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.ComunicacaoRequest;
import br.com.turmadobem.backend.dto.ComunicacaoResponse;
import br.com.turmadobem.backend.service.ComunicacaoService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/comunicacoes")
public class ComunicacaoResource {
    private final ComunicacaoService comunicacoes;

    public ComunicacaoResource(ComunicacaoService comunicacoes) { this.comunicacoes = comunicacoes; }

    @GET
    public Response list() { return Response.ok(comunicacoes.listAll().stream().map(ComunicacaoResponse::from).toList()).build(); }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) { return Response.ok(ComunicacaoResponse.from(comunicacoes.findById(id))).build(); }

    @POST
    public Response create(@Valid ComunicacaoRequest request) {
        var comunicacao = comunicacoes.create(request);
        return Response.created(URI.create("/api/comunicacoes/" + comunicacao.id)).entity(ComunicacaoResponse.from(comunicacao)).build();
    }

    @PATCH
    @Path("/{id}/read")
    public Response read(@PathParam("id") Long id) { return Response.ok(ComunicacaoResponse.from(comunicacoes.markRead(id))).build(); }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        comunicacoes.delete(id);
        return Response.noContent().build();
    }
}
