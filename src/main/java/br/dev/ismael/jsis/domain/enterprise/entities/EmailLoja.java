package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity(name = "emailLoja")
public class EmailLoja {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idEmailLoja")
    private UUID idEmailLoja;

    @Column()
    private String titulo;

    @Column(name = "recebeLeads", columnDefinition = "true")
    private Boolean recebeLeads;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;

    @CreationTimestamp
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
}
