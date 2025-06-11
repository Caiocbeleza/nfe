package br.com.nfe.service;

import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.dto.CriarNotaDTO;
import br.com.nfe.domain.dto.NotaFiscalDTO;
import br.com.nfe.domain.dto.NotaFiscalResponseDTO;

public interface NotaFiscalService {
    NotaFiscalResponseDTO criarNota(CriarNotaDTO dto);

    NotaFiscal enviarParaSefaz(Long id);
}
