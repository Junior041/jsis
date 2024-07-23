package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.EnderecoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnderecoPessoaRepository extends JpaRepository<EnderecoPessoa, UUID> {
}
