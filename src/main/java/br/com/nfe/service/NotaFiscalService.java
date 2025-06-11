package br.com.nfe.service;

import br.com.nfe.domain.Emitente;
import br.com.nfe.domain.ItemNota;
import br.com.nfe.domain.NotaFiscal;
import br.com.nfe.domain.Produto;
import br.com.nfe.domain.dto.ItemNotaDTO;
import br.com.nfe.domain.dto.NotaFiscalDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class NotaFiscalService {
    @Transactional
    public NotaFiscal criarNota(NotaFiscalDTO dto) {
        Emitente emitente = Emitente.findById(dto.emitenteId);
        if(emitente == null){
            throw new WebApplicationException("Emitente não encontrado", 400);
        }


        NotaFiscal nota = new NotaFiscal();
        nota.emitente = emitente;
        nota.destinatario = dto.destinatario;
        nota.itens = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for (ItemNotaDTO itemDTO : dto.itens) {
            Produto produto = Produto.findById(itemDTO.produtoId);
            if (produto == null) {
                throw new WebApplicationException("Produto não encontrado: " + itemDTO.produtoId, 400);
            }

            ItemNota item = new ItemNota();
            item.produto = produto;
            item.quantidade = itemDTO.quantidade;
            item.valorTotal = produto.valorUnitario.multiply(BigDecimal.valueOf(itemDTO.quantidade));

            nota.itens.add(item);
            total = total.add(item.valorTotal);
        }

        nota.totalNota = total;
        nota.icms = total.multiply(new BigDecimal("0.18")).setScale(2);
        nota.totalComImposto = total.add(nota.icms);

        nota.persist();
        return nota;
    }

    public NotaFiscal enviarParaSefaz(Long id) {
        NotaFiscal nota = NotaFiscal.findById(id);
        if (nota == null) {
            throw new WebApplicationException("Nota não encontrada", 404);
        }

        // Gerar protocolo fictício
        nota.protocoloAutorizacao = UUID.randomUUID().toString();

        // Construir XML simulado
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<nfe>\n");

        // Emitente
        xml.append("  <emitente>\n");
        xml.append("    <cnpj>").append(nota.emitente.cnpj).append("</cnpj>\n");
        xml.append("    <razaoSocial>").append(nota.emitente.razaoSocial).append("</razaoSocial>\n");
        xml.append("    <ie>").append(nota.emitente.ie).append("</ie>\n");
        xml.append("    <uf>").append(nota.emitente.uf).append("</uf>\n");
        xml.append("  </emitente>\n");

        // Destinatário
        xml.append("  <destinatario>\n");
        xml.append("    <nome>").append(nota.destinatario.nome).append("</nome>\n");
        xml.append("    <documento>").append(nota.destinatario.documento).append("</documento>\n");
        xml.append("    <uf>").append(nota.destinatario.uf).append("</uf>\n");
        xml.append("  </destinatario>\n");

        // Itens
        xml.append("  <itens>\n");
        for (ItemNota item : nota.itens) {
            xml.append("    <item>\n");
            xml.append("      <produto>").append(item.produto.nome).append("</produto>\n");
            xml.append("      <quantidade>").append(item.quantidade).append("</quantidade>\n");
            xml.append("      <valorTotal>").append(item.valorTotal).append("</valorTotal>\n");
            xml.append("    </item>\n");
        }
        xml.append("  </itens>\n");

        // Totais
        xml.append("  <totais>\n");
        xml.append("    <totalNota>").append(nota.totalNota).append("</totalNota>\n");
        xml.append("    <icms>").append(nota.icms).append("</icms>\n");
        xml.append("    <totalComImposto>").append(nota.totalComImposto).append("</totalComImposto>\n");
        xml.append("    <protocolo>").append(nota.protocoloAutorizacao).append("</protocolo>\n");
        xml.append("  </totais>\n");

        xml.append("</nfe>");

        nota.xml = xml.toString();
        nota.persist();

        return nota;

    }
}
