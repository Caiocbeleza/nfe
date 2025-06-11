package br.com.nfe.service.impl;

import br.com.nfe.domain.Emitente;
import br.com.nfe.domain.dto.EmitenteDTO;
import br.com.nfe.service.EmitenteService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EmitenteServiceImpl implements EmitenteService {

    @Transactional
    public Emitente cadastrar(EmitenteDTO dto) {
        Emitente emitente = new Emitente();
        emitente.cnpj = dto.cnpj;
        emitente.razaoSocial = dto.razaoSocial;
        emitente.ie = dto.ie;
        emitente.uf = dto.uf;

        try {
            emitente.persist();
        }catch (Exception e){
            System.out.println("Erro ao salvar Emitente: " + e.getMessage());
        }
        return emitente;
    }
}
