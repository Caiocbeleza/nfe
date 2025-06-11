package br.com.nfe.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends PanacheEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "emitente_id", nullable = false)
    public Emitente emitente;

    @Embedded
    public Destinatario destinatario;

    @OneToMany(mappedBy = "notaFiscal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    public List<ItemNota> itens;

    @Column(name = "total_nota", nullable = false, precision = 10, scale = 2)
    public BigDecimal totalNota;

    @Column(nullable = false, precision = 10, scale = 2)
    public BigDecimal icms;

    @Column(name = "total_com_imposto", nullable = false, precision = 10, scale = 2)
    public BigDecimal totalComImposto;

    @Column(name = "protocolo_autorizacao", length = 100)
    public String protocoloAutorizacao;

    @Column(columnDefinition = "CLOB")
    public String xml;
}

