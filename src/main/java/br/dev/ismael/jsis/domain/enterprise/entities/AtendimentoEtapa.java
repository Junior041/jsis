package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "atendimentoEtapa")
public class AtendimentoEtapa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idAtendimentoEtapa")
    private Integer idAtendimentoEtapa;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fkAtendimento", referencedColumnName = "idAtendimento", nullable = false)
    private Atendimento atendimento;

    @ManyToOne
    @JoinColumn(name = "fkEtapa", referencedColumnName = "idEtapa", nullable = false)
    private Etapa etapa;

    @ManyToOne
    @JoinColumn(name = "fkUsuario", referencedColumnName = "idUsuario", nullable = false)
    private Usuario usuario;
}
