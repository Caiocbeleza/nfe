package br.com.nfe.service;

import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.dto.nota.CriarNotaDTO;
import br.com.nfe.domain.dto.nota.NotaFiscalResponseDTO;

public interface NotaFiscalService {
    NotaFiscalResponseDTO criarNota(CriarNotaDTO dto);

    NotaFiscal enviarParaSefaz(Long id);
}
