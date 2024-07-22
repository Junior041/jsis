package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtapaRepository extends JpaRepository<Etapa, Integer> {
    List<Etapa> findByEtapaByDepartamentoId(Integer idDepartamento);

    @Query("SELECT MAX(e.prioridade) FROM Etapa e WHERE e.departamento.idDepartamento = :idDepartamento")
    Integer findMaxPrioridadeByDepartamentoId(@Param("idDepartamento") Integer idDepartamento);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM Etapa e WHERE e.departamento.idDepartamento = :idDepartamento AND e.prioridade = :prioridade")
    boolean findIfPrioridadeAlreadyExists(@Param("idDepartamento") Integer idDepartamento, @Param("prioridade") Integer prioridade);
}
