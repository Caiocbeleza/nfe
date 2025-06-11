package br.com.nfe.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_nota")
public class ItemNota extends PanacheEntity {


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    public Produto produto;

    @Column(nullable = false)
    public int quantidade;

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    public BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id")
    @JsonBackReference
    public NotaFiscal notaFiscal;
}
