package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.dto.EspecialidadeResponse;
import br.com.turmadobem.backend.service.EspecialidadeService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/especialidades")
public class EspecialidadeResource {
    private final EspecialidadeService especialidades;

    public EspecialidadeResource(EspecialidadeService especialidades) { this.especialidades = especialidades; }

    @GET
    public Response list() {
        return Response.ok(especialidades.listAll().stream().map(EspecialidadeResponse::from).toList()).build();
    }
}
