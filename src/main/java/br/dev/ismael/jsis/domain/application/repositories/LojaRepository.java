package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface LojaRepository extends JpaRepository<Loja, UUID> {
    Optional<Loja> findByCpfCnpj(String cpfCnpj);
}
