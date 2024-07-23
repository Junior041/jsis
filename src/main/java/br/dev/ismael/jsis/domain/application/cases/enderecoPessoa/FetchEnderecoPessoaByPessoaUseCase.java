package br.dev.ismael.jsis.domain.application.cases.enderecoPessoa;

import br.dev.ismael.jsis.domain.application.dto.enderecoPessoa.EnderecoPessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EnderecoPessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EnderecoPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchEnderecoPessoaByPessoaUseCase {
    @Autowired
    private EnderecoPessoaRepository enderecoPessoaRepository;

    public List<EnderecoPessoaResponseDTO> execute(UUID idPessoa){
        List<EnderecoPessoa> enderecoPessoas = this.enderecoPessoaRepository.findEnderecoPessoaByPessoaIdPessoa(idPessoa);
        return enderecoPessoas.stream().map(EnderecoPessoaResponseDTO::transformToDTO).toList();
    }
}
