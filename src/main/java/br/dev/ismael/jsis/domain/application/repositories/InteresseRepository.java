package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Interesse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteresseRepository extends JpaRepository<Interesse, Integer> {
}
