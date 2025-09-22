package com.exemplo.produtos.resource;

import com.exemplo.produtos.dto.ProdutoRequest;
import com.exemplo.produtos.mapper.ProdutoMapper;
import com.exemplo.produtos.repository.ProdutoRepository;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/produtos")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ProdutoResource {

    private ProdutoRepository produtoRepository;

    public ProdutoResource() {}

    @POST
    public Response createProduct(ProdutoRequest request) {
        try {
            var produtoSalvo = getProdutoRepository().save(ProdutoMapper.toEntity(request));

            return Response.status(201)
                    .entity(produtoSalvo)
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao criar produto: " + e.getMessage())
                    .build();
        }
    }

    private ProdutoRepository getProdutoRepository() {
        if (produtoRepository == null) {
            produtoRepository = new ProdutoRepository();
        }
        return produtoRepository;
    }

    @GET
    public Response listAll() {
        var products = getProdutoRepository().findAll();
        return Response.ok(products).build();
    }
}
