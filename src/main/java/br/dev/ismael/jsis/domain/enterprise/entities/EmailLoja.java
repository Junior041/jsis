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
    @Column(name = "id_email_loja")
    private UUID idEmailLoja;

    @Column()
    private String titulo;

    @Column(name = "recebe_leads", columnDefinition = "true")
    private Boolean recebeLeads;

    @ManyToOne
    @JoinColumn(name = "fk_loja", referencedColumnName = "id_loja", nullable = false)
    private Loja loja;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
