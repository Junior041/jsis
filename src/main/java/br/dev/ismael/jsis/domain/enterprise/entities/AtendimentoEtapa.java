package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "atendimento_etapa")
public class AtendimentoEtapa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_atendimento_etapa")
    private Integer idAtendimentoEtapa;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "fk_atendimento", referencedColumnName = "id_atendimento", nullable = false)
    private Atendimento atendimento;

    @ManyToOne
    @JoinColumn(name = "fk_etapa", referencedColumnName = "id_etapa", nullable = false)
    private Etapa etapa;

    @ManyToOne
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;
}
