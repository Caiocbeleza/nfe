package br.com.nfe.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "emitente")
public class Emitente extends PanacheEntity {

    @Column(nullable = false, length = 14)
    public String cnpj;

    @Column(name = "razao_social", nullable = false, length = 100)
    public String razaoSocial;

    @Column(nullable = false, length = 20)
    public String ie;

    @Column(nullable = false, length = 2)
    public String uf;
}
