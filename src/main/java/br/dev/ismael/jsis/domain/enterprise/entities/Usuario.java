package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario")
    private UUID idUsuario ;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToOne
    @JoinColumn(name = "fk_departamento", referencedColumnName = "id_departamento", nullable = true)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "fk_loja", referencedColumnName = "id_loja", nullable = false)
    private Loja loja;
}
