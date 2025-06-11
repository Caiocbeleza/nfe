package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "item_nota")
public class ItemNota extends PanacheEntity {

    @ManyToOne
    public Produto produto;

    public int quantidade;

    public BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id") // esse nome será usado no banco
    public NotaFiscal notaFiscal;
}
