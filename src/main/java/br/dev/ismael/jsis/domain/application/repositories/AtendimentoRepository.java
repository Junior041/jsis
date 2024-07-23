package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
}
