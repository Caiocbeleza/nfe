package br.com.nfe.service;

import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.dto.NotaFiscalDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.util.UUID;

@ApplicationScoped
public class NotaFiscalService {
    public NotaFiscal criarNota(NotaFiscalDTO dto) {
        // Validar, calcular e persistir nota
        // Simulação simples por enquanto
        return new NotaFiscal();
    }

    public NotaFiscal enviarParaSefaz(Long id) {
        NotaFiscal nota = NotaFiscal.findById(id);
        if (nota == null) {
            throw new WebApplicationException("Nota não encontrada", 404);
        }
        nota.protocoloAutorizacao = UUID.randomUUID().toString();
        nota.xml = "<xml>Simulação NF-e</xml>";
        nota.persist();
        return nota;
    }
}
