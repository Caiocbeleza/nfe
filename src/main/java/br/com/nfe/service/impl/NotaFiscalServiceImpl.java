package br.com.nfe.service.impl;

import br.com.nfe.domain.*;
import br.com.nfe.domain.dto.nota.CriarNotaDTO;
import br.com.nfe.domain.dto.nota.NotaFiscalDTO;
import br.com.nfe.service.NotaFiscalService;
import br.com.nfe.util.ValidadorNotaFiscal;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NotaFiscalServiceImpl implements NotaFiscalService {
    @Transactional
    public NotaFiscalDTO criarNota(CriarNotaDTO dto) {
        Emitente emitente = Emitente.findById(dto.emitenteId);
        if(emitente == null){
            throw new WebApplicationException("Emitente não encontrado", 400);
        }


        NotaFiscal nota = new NotaFiscal();
        nota.emitente = emitente;

        Destinatario dest = new Destinatario();
        dest.nome = dto.destinatario.nome;
        dest.documento = dto.destinatario.documento;
        dest.uf = dto.destinatario.uf;
        nota.destinatario = dest;

        BigDecimal totalNota = BigDecimal.ZERO;
        List<ItemNota> itens = new ArrayList<>();

        try {
            for (CriarNotaDTO.ItemNotaDTO itemDTO : dto.itens) {
                Produto produto = Produto.findById(itemDTO.produtoId);
                if (produto == null) {
                    throw new NotFoundException("Produto ID " + itemDTO.produtoId + " não encontrado");
                }

                ItemNota item = new ItemNota();
                item.produto = produto;
                item.quantidade = itemDTO.quantidade;
                item.valorTotal = produto.valorUnitario.multiply(BigDecimal.valueOf(itemDTO.quantidade));
                item.notaFiscal = nota;

                totalNota = totalNota.add(item.valorTotal);
                itens.add(item);
            }

            if (totalNota.compareTo(BigDecimal.ZERO) == 0) {
                throw new WebApplicationException(
                        Response.status(Response.Status.BAD_REQUEST)
                                .entity("Rejeição: Nota com valor zero.")
                                .build()
                );
            }

            nota.itens = itens;
            nota.totalNota = totalNota;
            nota.icms = totalNota.multiply(new BigDecimal("0.18"));
            nota.totalComImposto = nota.totalNota.add(nota.icms);
            nota.protocoloAutorizacao = UUID.randomUUID().toString();


            nota.persist();
        }catch (Exception e){
            System.out.println("Erro ao salvar a nota: " + e.getMessage());
        }
        return toResponseDTO(nota);
    }

    private NotaFiscalDTO toResponseDTO(NotaFiscal nota) {
        NotaFiscalDTO dto = new NotaFiscalDTO();
        dto.id = nota.id;
        dto.protocoloAutorizacao = nota.protocoloAutorizacao;
        dto.totalNota = nota.totalNota;
        dto.icms = nota.icms;
        dto.totalComImposto = nota.totalComImposto;

        var emitenteDTO = new NotaFiscalDTO.EmitenteDTO();
        emitenteDTO.cnpj = nota.emitente.cnpj;
        emitenteDTO.razaoSocial = nota.emitente.razaoSocial;
        emitenteDTO.ie = nota.emitente.ie;
        emitenteDTO.uf = nota.emitente.uf;
        dto.emitente = emitenteDTO;

        var destDTO = new CriarNotaDTO.DestinatarioDTO();
        destDTO.nome = nota.destinatario.nome;
        destDTO.documento = nota.destinatario.documento;
        destDTO.uf = nota.destinatario.uf;
        dto.destinatario = destDTO;

        dto.itens = new ArrayList<>();
        for (ItemNota item : nota.itens) {
            var itemDTO = new NotaFiscalDTO.ItemResponseDTO();
            itemDTO.quantidade = item.quantidade;
            itemDTO.valorTotal = item.valorTotal;
            dto.itens.add(itemDTO);
        }

        return dto;
    }

    public NotaFiscal enviarParaSefaz(Long id) {
        NotaFiscal nota = NotaFiscal.findById(id);
        if (nota == null) {
            throw new WebApplicationException("Nota não encontrada", 404);
        }

        // Gerar protocolo fictício
        nota.protocoloAutorizacao = UUID.randomUUID().toString();

        try {
            // Construir XML simulado
            StringBuilder xml = new StringBuilder();
            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            xml.append("<nfe>");

            // Emitente
            xml.append("<emitente>");
            xml.append("<cnpj>").append(nota.emitente.cnpj).append("</cnpj>");
            xml.append("<razaoSocial>").append(nota.emitente.razaoSocial).append("</razaoSocial>");
            xml.append("<ie>").append(nota.emitente.ie).append("</ie>");
            xml.append("<uf>").append(nota.emitente.uf).append("</uf>");
            xml.append("</emitente>");

            // Destinatário
            xml.append("<destinatario>");
            xml.append("<nome>").append(nota.destinatario.nome).append("</nome>");
            xml.append("<documento>").append(nota.destinatario.documento).append("</documento>");
            xml.append("<uf>").append(nota.destinatario.uf).append("</uf>");
            xml.append("</destinatario>");

            // Itens
            xml.append("<itens>");
            for (ItemNota item : nota.itens) {
                xml.append("<item>");
                xml.append("<produto>").append(item.produto.nome).append("</produto>");
                xml.append("<quantidade>").append(item.quantidade).append("</quantidade>");
                xml.append("<valorTotal>").append(item.valorTotal).append("</valorTotal>");
                xml.append("</item>");
            }
            xml.append("</itens>");

            // Totais
            xml.append("<totais>");
            xml.append("<totalNota>").append(nota.totalNota).append("</totalNota>");
            xml.append("<icms>").append(nota.icms).append("</icms>");
            xml.append("<totalComImposto>").append(nota.totalComImposto).append("</totalComImposto>");
            xml.append("<protocolo>").append(nota.protocoloAutorizacao).append("</protocolo>");
            xml.append("</totais>");

            xml.append("</nfe>");

            nota.xml = xml.toString();
            nota.persist();
        }catch (Exception e){
            System.out.println("Erro ao gerar xml: " + e.getMessage());
        }

        return nota;

    }
}
