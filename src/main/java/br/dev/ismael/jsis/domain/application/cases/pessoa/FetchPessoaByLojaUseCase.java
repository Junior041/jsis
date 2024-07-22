package br.dev.ismael.jsis.domain.application.cases.pessoa;

import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchPessoaByLojaUseCase {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> execute(UUID idLoja){
        List<Pessoa> pessoas = this.pessoaRepository.findPessoaByLojaIdLoja(idLoja);
        return pessoas.stream().map(PessoaResponseDTO::transformToDTO).toList();
    }

}
