package br.com.nfe.service;

import br.com.nfe.domain.Emitente;
import br.com.nfe.domain.dto.EmitenteDTO;

public interface EmitenteService {
    Emitente cadastrar(EmitenteDTO dto);

}
