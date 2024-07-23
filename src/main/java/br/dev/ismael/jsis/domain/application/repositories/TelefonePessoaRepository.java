package br.dev.ismael.jsis.domain.application.repositories;

import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TelefonePessoaRepository extends   JpaRepository<TelefonePessoa, UUID> {
    List<TelefonePessoa> findTelefonePessoaByPessoaIdPessoa(UUID idPessoa);
}
