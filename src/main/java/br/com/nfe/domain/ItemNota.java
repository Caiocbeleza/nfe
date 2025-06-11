package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class ItemNota extends PanacheEntity {
    @ManyToOne
    public Produto produto;

    public int quantidade;

    public BigDecimal valorTotal;
}
