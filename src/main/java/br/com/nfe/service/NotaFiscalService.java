package br.com.nfe.service;

import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.dto.nota.CriarNotaDTO;
import br.com.nfe.domain.dto.nota.NotaFiscalDTO;

public interface NotaFiscalService {
    NotaFiscalDTO criarNota(CriarNotaDTO dto);

    NotaFiscal enviarParaSefaz(Long id);
}
