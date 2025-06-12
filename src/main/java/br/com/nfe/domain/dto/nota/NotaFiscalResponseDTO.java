package br.com.nfe.domain.dto.nota;

import java.math.BigDecimal;
import java.util.List;

public class NotaFiscalResponseDTO {
    public Long id;
    public String protocoloAutorizacao;
    public BigDecimal totalNota;
    public BigDecimal icms;
    public BigDecimal totalComImposto;
    public EmitenteDTO emitente;
    public CriarNotaDTO.DestinatarioDTO destinatario;
    public List<ItemResponseDTO> itens;

    public static class EmitenteDTO {
        public String cnpj;
        public String razaoSocial;
        public String ie;
        public String uf;
    }

    public static class ItemResponseDTO {
        public int quantidade;
        public BigDecimal valorTotal;
    }
}
