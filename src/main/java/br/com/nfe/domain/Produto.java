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
    public String codigo;

    @NotBlank
    public String nome;

    @Pattern(regexp = "\\d{8}")
    public String ncm;

    @Pattern(regexp = "\\d{4}")
    public String cfop;

    @Positive
    public BigDecimal valorUnitario;
}