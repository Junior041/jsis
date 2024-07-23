package br.dev.ismael.jsis.domain.application.cases.telefonePessoa;

import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.TelefonePessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTelefonePessoaUseCase {
    @Autowired
    private TelefonePessoaRepository telefonePessoaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public TelefonePessoa execute(TelefonePessoaRequestDTO telefonePessoaRequestDTO){
        Pessoa pessoa = this.pessoaRepository.findById(telefonePessoaRequestDTO.getFkPessoa()).orElseThrow(DadoNaoEncontradoErro::new);
        TelefonePessoa telefonePessoa = TelefonePessoa.builder()
                .pessoa(pessoa)
                .telefone(telefonePessoaRequestDTO.getTelefone().replaceAll("[^0-9]", ""))
                .whatsapp(telefonePessoaRequestDTO.getWhatsapp())
                .ddd(telefonePessoaRequestDTO.getDdd().replaceAll("[^0-9]", ""))
                .build();
        return this.telefonePessoaRepository.save(telefonePessoa);
    }
}
