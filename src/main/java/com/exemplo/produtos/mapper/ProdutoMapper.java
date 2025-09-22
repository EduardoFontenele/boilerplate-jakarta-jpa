package com.exemplo.produtos.mapper;

import com.exemplo.produtos.dto.ProdutoRequest;
import com.exemplo.produtos.entity.Produto;

public abstract class ProdutoMapper {
    private ProdutoMapper() {}

    public static Produto toEntity(ProdutoRequest produtoRequest) {
        return new Produto(produtoRequest.nome(), produtoRequest.preco(), produtoRequest.descricao());
    }
}
