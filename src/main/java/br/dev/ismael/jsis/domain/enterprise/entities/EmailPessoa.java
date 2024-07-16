package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "email_pessoa")
public class EmailPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idEmailPessoa")
    private UUID idEmailPessoa ;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "recebeNovidades")
    private Boolean recebeNovidades;

    @ManyToOne
    @JoinColumn(name = "fkPessoa", referencedColumnName = "idPessoa", nullable = false)
    private Pessoa pessoa;
}
