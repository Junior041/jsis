package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity(name = "etapa")
@AllArgsConstructor
@NoArgsConstructor
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idEtapa")
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
    @JoinColumn(name = "fkDepartamento", referencedColumnName = "idDepartamento", nullable = false)
    private Departamento departamento;
}
