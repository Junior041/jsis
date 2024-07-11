package br.dev.ismael.jsis.domain.application.cases.loja;

import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllLojaUseCase {
    @Autowired
    private LojaRepository lojaRepository;

    public List<Loja> execute(){
        return this.lojaRepository.findAll();
    }
}
