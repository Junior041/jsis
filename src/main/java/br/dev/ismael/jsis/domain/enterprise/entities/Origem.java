package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "origem")
public class Origem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idOrigem")
    private Integer idOrigem;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;
}
