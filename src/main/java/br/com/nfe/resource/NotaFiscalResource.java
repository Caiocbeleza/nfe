package br.com.nfe.resource;

import br.com.nfe.domain.dto.CriarNotaDTO;
import br.com.nfe.domain.dto.NotaFiscalDTO;
import br.com.nfe.service.impl.NotaFiscalServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/notas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotaFiscalResource {
    @Inject
    NotaFiscalServiceImpl service;

    @POST
    public Response criarNota(CriarNotaDTO dto) {
        return Response.ok(service.criarNota(dto)).build();
    }

    @POST
    @Path("/{id}/enviar")
    public Response enviarNota(@PathParam("id") Long id) {
        return Response.ok(service.enviarParaSefaz(id)).build();
    }
}
