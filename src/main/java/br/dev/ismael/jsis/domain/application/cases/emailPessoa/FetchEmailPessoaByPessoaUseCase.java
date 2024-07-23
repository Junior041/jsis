package br.dev.ismael.jsis.domain.application.cases.emailPessoa;

import br.dev.ismael.jsis.domain.application.dto.emailPessoa.EmailPessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EmailPessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchEmailPessoaByPessoaUseCase {
    @Autowired
    private EmailPessoaRepository emailPessoaRepository;

    public List<EmailPessoaResponseDTO> execute(UUID idPessoa){
        List<EmailPessoa> emailPessoas = this.emailPessoaRepository.findEmailPessoaByPessoaIdPessoa(idPessoa);
        return emailPessoas.stream().map(EmailPessoaResponseDTO::transformToDTO).toList();
    }
}
