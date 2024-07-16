package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity(name = "loja")
@AllArgsConstructor
@NoArgsConstructor
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
