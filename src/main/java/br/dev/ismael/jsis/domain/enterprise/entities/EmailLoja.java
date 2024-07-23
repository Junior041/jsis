package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity(name = "email_loja")
public class EmailLoja {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idEmailLoja")
    private UUID idEmailLoja;

    private String titulo;
    private String email;

    @Column(name = "recebeLeads")
    private Boolean recebeLeads;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
