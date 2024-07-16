package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
@Entity(name = "enderecoPessoa")
public class EnderecoPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idEnderecoPessoa")
    private UUID idEnderecoPessoa ;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String logradouro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String rua;

    @Column()
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "fkPessoa", referencedColumnName = "idPessoa", nullable = false)
    private Pessoa pessoa;
}
