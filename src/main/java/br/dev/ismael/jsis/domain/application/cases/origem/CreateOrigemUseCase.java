package br.dev.ismael.jsis.domain.application.cases.origem;

import br.dev.ismael.jsis.domain.application.dto.origem.OrigemRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrigemUseCase {
    @Autowired
    private OrigemRepository origemRepository;

    public Origem execute(OrigemRequestDTO origemRequestDTO){
        this.origemRepository.findByNome(origemRequestDTO.getNome()).ifPresent((origem) -> new JaCadastradoErro());
        Origem origem = Origem.builder()
                .descricao(origemRequestDTO.getDescricao())
                .nome(origemRequestDTO.getNome())
                .build();
        return this.origemRepository.save(origem);
    }
}
