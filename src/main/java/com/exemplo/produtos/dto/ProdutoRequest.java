package com.exemplo.produtos.dto;

import java.math.BigDecimal;

public record ProdutoRequest(String nome, BigDecimal preco, String descricao) {
}
