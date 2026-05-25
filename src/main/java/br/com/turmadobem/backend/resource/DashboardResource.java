package br.com.turmadobem.backend.resource;

import br.com.turmadobem.backend.service.DashboardService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/dashboard")
public class DashboardResource {
    private final DashboardService dashboard;

    public DashboardResource(DashboardService dashboard) { this.dashboard = dashboard; }

    @GET
    @Path("/summary")
    public Response summary() { return Response.ok(dashboard.buildDashboardSummary()).build(); }
}
