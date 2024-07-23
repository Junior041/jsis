package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.EmailLoja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailLojaRepository extends JpaRepository<EmailLoja, UUID> {
}
