package br.com.nfe.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public class Destinatario {
    @NotBlank
    public String nome;

    @NotBlank
    public String documento;

    @NotBlank
    public String uf;
}
