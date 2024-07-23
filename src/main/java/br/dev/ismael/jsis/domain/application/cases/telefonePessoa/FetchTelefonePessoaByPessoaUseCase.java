package br.dev.ismael.jsis.domain.application.cases.telefonePessoa;

import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.TelefonePessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchTelefonePessoaByPessoaUseCase {
    @Autowired
    private TelefonePessoaRepository telefonePessoaRepository;

    public List<TelefonePessoaResponseDTO> execute(UUID idPessoa){
        List<TelefonePessoa> telefonePessoas = this.telefonePessoaRepository.findTelefonePessoaByPessoaIdPessoa(idPessoa);
        return telefonePessoas.stream().map(TelefonePessoaResponseDTO::transformToDTO).toList();
    }
}
