package br.com.nfe.service.impl;

import br.com.nfe.domain.Produto;
import br.com.nfe.domain.dto.ProdutoDTO;
import br.com.nfe.service.ProdutoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Transactional
    public Produto cadastrar(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.codigo = dto.codigo;
        produto.nome = dto.nome;
        produto.ncm = dto.ncm;
        produto.cfop = dto.cfop;
        produto.valorUnitario = dto.valorUnitario;

        try {
            produto.persist();
        }catch (Exception e){
            System.out.println("Erro ao salvar o produto: " + e.getMessage());
        }
        return produto;
    }
}
