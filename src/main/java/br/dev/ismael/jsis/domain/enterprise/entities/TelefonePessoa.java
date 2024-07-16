package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "telefonePessoa")
public class TelefonePessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idTelefonePessoa")
    private UUID idTelefonePessoa ;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String ddd;

    @Column(nullable = false, columnDefinition = "false", name = "whatsapp")
    private Boolean whatsapp;

    @ManyToOne
    @JoinColumn(name = "fkPessoa", referencedColumnName = "idPessoa", nullable = false)
    private Pessoa pessoa;
}
