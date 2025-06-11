package br.com.nfe.domain.dto;

import br.com.nfe.domain.Destinatario;

import java.util.List;

public class NotaFiscalDTO {
    public Long emitenteId;
    public Destinatario destinatario;
    public List<ItemNotaDTO> itens;
}

