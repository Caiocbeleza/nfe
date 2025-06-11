package br.com.nfe.service;

import br.com.nfe.domain.Emitente;
import br.com.nfe.domain.dto.EmitenteDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmitenteService {

    @Transactional
    public Emitente cadastrar(EmitenteDTO dto) {
        Emitente emitente = new Emitente();
        emitente.cnpj = dto.cnpj;
        emitente.razaoSocial = dto.razaoSocial;
        emitente.ie = dto.ie;
        emitente.uf = dto.uf;


        emitente.persist();
        return emitente;
    }
}
