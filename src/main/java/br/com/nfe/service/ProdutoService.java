package br.com.nfe.service;

import br.com.nfe.domain.Produto;
import br.com.nfe.domain.dto.ProdutoDTO;

public interface ProdutoService {

    Produto cadastrar(ProdutoDTO dto);
}
