package br.com.nfe.resource;

import br.com.nfe.domain.Emitente;
import br.com.nfe.domain.dto.EmitenteDTO;
import br.com.nfe.service.EmitenteService;
import br.com.nfe.util.ValidadorCNPJ;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/emitentes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmitenteResource {

    @Inject
    EmitenteService service;

    @POST
    public Response cadastrar(EmitenteDTO dto){
        if (!ValidadorCNPJ.isCnpjValido(dto.cnpj)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("CNPJ inválido").build();
        }

        var emitente = service.cadastrar(dto);

        return Response.status(Response.Status.CREATED).entity(emitente).build();
    }

    @GET
    public List<Emitente> listar() {
        return Emitente.listAll();
    }
}
