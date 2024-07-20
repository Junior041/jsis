package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    List<Departamento> findByLojaIdLoja(UUID idLoja);
    Optional<Departamento> findByTituloAndLojaIdLoja(String titulo, UUID idLoja);
}
