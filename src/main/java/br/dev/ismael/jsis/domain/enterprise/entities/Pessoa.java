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
    @Column(name = "id_pessoa")
    private UUID idPessoa ;

    @Column(nullable = false, name = "primeiro_nome")
    private String primeiroNome;

    @Column(nullable = false, name = "ultimo_nome")
    private String ultimoNome;

    @Column(nullable = false)
    private String cpf;

    @Column()
    private String apelido;

    @Column()
    private String rg;

    @Column(name = "data_nascimento")
    private String dataNascimento;

    @ManyToOne
    @JoinColumn(name = "cadastradoPorUsuarioId", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_loja", referencedColumnName = "id_loja", nullable = false)
    private Loja loja;
}
