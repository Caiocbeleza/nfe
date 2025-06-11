package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Emitente extends PanacheEntity {
    @NotBlank
    public String cnpj;

    @NotBlank
    public String razaoSocial;

    @NotBlank
    public String ie;

    @NotBlank
    public String uf;
}
