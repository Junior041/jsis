package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EtapaRepository extends JpaRepository<Etapa, Integer> {
    List<Etapa> findByFkDepartamento(Integer fkDepartamento);
}
