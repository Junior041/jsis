package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "telefone_pessoa")
public class TelefonePessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_telefone_pessoa")
    private UUID idTelefonePessoa ;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false, columnDefinition = "false", name = "whatsapp")
    private Boolean whatsapp;

    @ManyToOne
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}
