package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "idDepartamento")
    private Integer idDepartamento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String titulo;

    @Column()
    private String icone;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;
}
