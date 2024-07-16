package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idPessoa")
    private UUID idPessoa ;

    @Column(nullable = false, name = "primeiroNome")
    private String primeiroNome;

    @Column(nullable = false, name = "ultimoNome")
    private String ultimoNome;

    @Column(nullable = false)
    private String cpf;

    @Column()
    private String apelido;

    @Column()
    private String rg;

    @Column(name = "dataNascimento")
    private String dataNascimento;

    @ManyToOne
    @JoinColumn(name = "cadastradoPorUsuarioId", referencedColumnName = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;
}
