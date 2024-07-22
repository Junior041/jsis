package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    Optional<Pessoa> findByCpfAndLojaIdLoja(String cpf, UUID idLoja);
    List<Pessoa> findPessoaByLojaIdLoja(UUID idLoja);
}
