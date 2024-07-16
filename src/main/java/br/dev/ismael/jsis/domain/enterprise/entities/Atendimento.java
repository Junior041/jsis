package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "atendimento")
public class Atendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAtendimento")
    private Integer idAtendimento;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fkOrigem", referencedColumnName = "idOrigem", nullable = false)
    private Origem origem;

    @ManyToOne
    @JoinColumn(name = "fkInteresse", referencedColumnName = "idInteresse", nullable = false)
    private Interesse interesse;

    @ManyToOne
    @JoinColumn(name = "fkPessoa", referencedColumnName = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;
}
