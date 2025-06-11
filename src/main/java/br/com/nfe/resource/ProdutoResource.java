package br.com.nfe.resource;

import br.com.nfe.domain.Produto;
import br.com.nfe.domain.dto.ProdutoDTO;
import br.com.nfe.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/produtos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @POST
    public Response cadastrar(ProdutoDTO dto) {
        if (!dto.ncm.matches("\\d{8}")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("NCM inválido").build();
        }
        if (!dto.cfop.matches("\\d{4}")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CFOP inválido").build();
        }


        var produto = service.cadastrar(dto);

        return Response.status(Response.Status.CREATED).entity(produto).build();
    }

    @GET
    public List<Produto> listar() {
        return Produto.listAll();
    }
}
