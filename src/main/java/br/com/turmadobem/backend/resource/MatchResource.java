package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.MatchRequest;
import br.com.turmadobem.backend.dto.MatchResponse;
import br.com.turmadobem.backend.dto.MatchStatusRequest;
import br.com.turmadobem.backend.exception.BusinessException;
import br.com.turmadobem.backend.service.MatchService;
import jakarta.validation.Valid;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;

@Path("/api/matches")
public class MatchResource {
    private final MatchService matches;

    public MatchResource(MatchService matches) { this.matches = matches; }

    @GET
    public Response list() { return Response.ok(matches.listAll().stream().map(MatchResponse::from).toList()).build(); }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) { return Response.ok(MatchResponse.from(matches.findById(id))).build(); }

    @GET
    @Path("/recommendations")
    public Response recommendations(@QueryParam("patientId") Long patientId) {
        if (patientId == null) {
            throw new BusinessException(Response.Status.BAD_REQUEST, "missing_patient_id", "patientId é obrigatório");
        }
        return Response.ok(matches.recommendDentistsForPatient(patientId)).build();
    }

    @POST
    public Response create(@Valid MatchRequest request) {
        var match = matches.create(request);
        return Response.created(URI.create("/api/matches/" + match.id)).entity(MatchResponse.from(match)).build();
    }

    @PATCH
    @Path("/{id}/status")
    public Response status(@PathParam("id") Long id, @Valid MatchStatusRequest request) {
        return Response.ok(MatchResponse.from(matches.updateMatchStatus(id, request.status()))).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        matches.delete(id);
        return Response.noContent().build();
    }
}
