package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity(name = "loja")
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_loja")
    private UUID idLoja;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(unique = true, name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
