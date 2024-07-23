package br.dev.ismael.jsis.domain.application.cases.emailPessoa;

import br.dev.ismael.jsis.domain.application.dto.emailPessoa.EmailPessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EmailPessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailPessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEmailPessoaUseCae {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EmailPessoaRepository emailPessoaRepository;

    public EmailPessoa execute(EmailPessoaRequestDTO emailPessoaRequestDTO) {
        Pessoa pessoa = this.pessoaRepository.findById(emailPessoaRequestDTO.getFkPessoa()).orElseThrow(DadoNaoEncontradoErro::new);
        EmailPessoa emailPessoa = EmailPessoa.builder()
                .email(emailPessoaRequestDTO.getEmail())
                .recebeNovidades(emailPessoaRequestDTO.getRecebeNovidades())
                .pessoa(pessoa)
                .build();
        return this.emailPessoaRepository.save(emailPessoa);
    }
}
