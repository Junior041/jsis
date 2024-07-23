package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.EmailPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailPessoaRepository extends JpaRepository<EmailPessoa, UUID> {
}
