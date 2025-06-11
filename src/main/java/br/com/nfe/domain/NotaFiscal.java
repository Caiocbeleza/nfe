package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class NotaFiscal extends PanacheEntity {
    @ManyToOne
    public Emitente emitente;

    @Embedded
    public Destinatario destinatario;

    @OneToMany(cascade = CascadeType.ALL)
    public List<ItemNota> itens;

    public BigDecimal totalNota;
    public BigDecimal icms;
    public BigDecimal totalComImposto;
    public String protocoloAutorizacao;
    public String xml;
}

