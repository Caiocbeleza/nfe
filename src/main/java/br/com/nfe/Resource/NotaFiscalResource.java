package br.com.nfe.Resource;

import br.com.nfe.domain.dto.NotaFiscalDTO;
import br.com.nfe.service.NotaFiscalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotaFiscalResource {
    @Inject
    NotaFiscalService service;

    @POST
    public Response criarNota(NotaFiscalDTO dto) {
        return Response.ok(service.criarNota(dto)).build();
    }

    @POST
    @Path("/{id}/enviar")
    public Response enviarNota(@PathParam("id") Long id) {
        return Response.ok(service.enviarParaSefaz(id)).build();
    }
}
