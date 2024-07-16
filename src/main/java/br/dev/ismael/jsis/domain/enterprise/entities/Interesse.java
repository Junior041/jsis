package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "interesse")
public class Interesse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idInteresse")
    private Integer idInteresse;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

}
