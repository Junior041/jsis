package br.dev.ismael.jsis.domain.application.cases.origem;

import br.dev.ismael.jsis.domain.application.dto.origem.OrigemResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllOrigemUseCase {
    @Autowired
    private OrigemRepository origemRepository;
    public List<OrigemResponseDTO> execute(){

        List<Origem> origems = this.origemRepository.findAll();
        return origems.stream().map(OrigemResponseDTO::transformaToDTO).toList();
    }
}
