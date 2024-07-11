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
    @Column(name = "id_email_pessoa")
    private UUID idEmailPessoa ;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "false", name = "recebe_novidades")
    private Boolean recebeNovidades;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}
