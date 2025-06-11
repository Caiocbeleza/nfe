package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto extends PanacheEntity {

    @NotBlank
    @Column(nullable = false, length = 100)
    public String codigo;

    @NotBlank
    @Column(nullable = false, length = 100)
    public String nome;

    @Pattern(regexp = "\\d{8}")
    @Column(nullable = false, length = 100)
    public String ncm;

    @Pattern(regexp = "\\d{4}")
    @Column(nullable = false, length = 100)
    public String cfop;

    @Positive
    @Column(name = "valor_unitario", nullable = false, length = 100)
    public BigDecimal valorUnitario;
}