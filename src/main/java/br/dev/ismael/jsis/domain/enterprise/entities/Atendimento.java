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
    @Column(name = "id_atendimento")
    private Integer idAtendimento;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_origem", referencedColumnName = "id_origem", nullable = false)
    private Origem origem;

    @ManyToOne
    @JoinColumn(name = "fk_interesse", referencedColumnName = "id_interesse", nullable = false)
    private Interesse interesse;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "fk_loja", referencedColumnName = "id_loja", nullable = false)
    private Loja loja;
}
