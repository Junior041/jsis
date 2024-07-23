package br.dev.ismael.jsis.domain.application.cases.enderecoPessoa;

import br.dev.ismael.jsis.domain.application.dto.enderecoPessoa.EnderecoPessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EnderecoPessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EnderecoPessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEnderecoPessoaUseCase {
    @Autowired
    private EnderecoPessoaRepository enderecoPessoaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public EnderecoPessoa execute(EnderecoPessoaRequestDTO enderecoPessoaRequestDTO) {
        Pessoa pessoa = this.pessoaRepository.findById(enderecoPessoaRequestDTO.getFkPessoa()).orElseThrow(DadoNaoEncontradoErro::new);
        EnderecoPessoa enderecoPessoa = EnderecoPessoa.builder()
                .cep(enderecoPessoaRequestDTO.getCep())
                .rua(enderecoPessoaRequestDTO.getRua())
                .cidade(enderecoPessoaRequestDTO.getCidade())
                .estado(enderecoPessoaRequestDTO.getEstado())
                .logradouro(enderecoPessoaRequestDTO.getLogradouro())
                .pessoa(pessoa)
                .build();
        return this.enderecoPessoaRepository.save(enderecoPessoa);
    }
}
