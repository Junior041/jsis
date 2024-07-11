package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Entity(name = "etapa")
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_etapa")
    private Integer idEtapa;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column()
    private String prioridade;

    @Column()
    private String corHexadecimal;

    @ManyToOne
    @JoinColumn(name = "fk_departamento", referencedColumnName = "id_departamento", nullable = false)
    private Departamento departamento;
}
