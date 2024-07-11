package br.dev.ismael.jsis.domain.application.cases.origem;

import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrigemUseCase {
    @Autowired
    private OrigemRepository origemRepository;
    public List<Origem> execute(){
        return this.origemRepository.findAll();
    }
}
