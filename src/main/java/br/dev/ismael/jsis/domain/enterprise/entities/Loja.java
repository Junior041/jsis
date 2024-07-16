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
    @Column(name = "idLoja")
    private UUID idLoja;

    @Column(name = "razaoSocial")
    private String razaoSocial;

    @Column(unique = true, name = "cpfCnpj")
    private String cpfCnpj;

    @Column(name = "nomeResponsavel")
    private String nomeResponsavel;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
