package br.dev.ismael.jsis.domain.application.cases.interesse;

import br.dev.ismael.jsis.domain.application.dto.interesse.InteresseRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.InteresseRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Interesse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateInteresseUseCase {
    @Autowired
    private InteresseRepository interesseRepository;

    public Interesse execute(InteresseRequestDTO interesseRequestDTO){
        Interesse interesse = Interesse.builder()
                .titulo(interesseRequestDTO.getTitulo())
                .descricao(interesseRequestDTO.getDescricao())
                .build();
        return this.interesseRepository.save(interesse);
    }
}
