package br.com.nfe.domain.dto;

import java.util.List;

public class CriarNotaDTO {
    public Long emitenteId;
    public DestinatarioDTO destinatario;
    public List<ItemNotaDTO> itens;

    public static class DestinatarioDTO {
        public String nome;
        public String documento;
        public String uf;
    }

    public static class ItemNotaDTO {
        public Long produtoId;
        public int quantidade;
    }
}
