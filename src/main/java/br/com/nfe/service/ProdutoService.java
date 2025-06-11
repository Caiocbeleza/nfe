package br.com.nfe.service;

import br.com.nfe.domain.Produto;
import br.com.nfe.domain.dto.ProdutoDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoService {

    @Transactional
    public Produto cadastrar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.codigo = dto.codigo;
        produto.nome = dto.nome;
        produto.ncm = dto.ncm;
        produto.cfop = dto.cfop;
        produto.valorUnitario = dto.valorUnitario;
        produto.persist();

        return produto;
    }
}
