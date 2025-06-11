package br.com.nfe.service;

import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.dto.NotaFiscalDTO;

public interface NotaFiscalService {
    NotaFiscal criarNota(NotaFiscalDTO dto);

    NotaFiscal enviarParaSefaz(Long id);
}
