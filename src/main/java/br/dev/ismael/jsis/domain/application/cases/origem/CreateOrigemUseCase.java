package br.dev.ismael.jsis.domain.application.cases.origem;

import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrigemUseCase {
    @Autowired
    private OrigemRepository origemRepository;

    public Origem execute(String nome, String descricao){
        this.origemRepository.findByNome(nome).ifPresent((origem) -> new JaCadastradoErro());
        Origem origem = Origem.builder()
                .descricao(descricao)
                .nome(nome)
                .build();
        return this.origemRepository.save(origem);
    }
}
