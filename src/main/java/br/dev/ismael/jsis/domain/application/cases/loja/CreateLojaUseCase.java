package br.dev.ismael.jsis.domain.application.cases.loja;

import br.dev.ismael.jsis.domain.application.dto.loja.LojaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLojaUseCase {

    @Autowired
    private LojaRepository lojaRepository;

    public Loja execute(LojaRequestDTO lojaRequestDTO){
        this.lojaRepository.findByCpfCnpj(lojaRequestDTO.getCpfCnpj()).ifPresent((loja) -> new JaCadastradoErro());
        Loja newLoja = Loja.builder()
                .cpfCnpj(lojaRequestDTO.getCpfCnpj())
                .nomeResponsavel(lojaRequestDTO.getNomeResponsavel())
                .razaoSocial(lojaRequestDTO.getRazaoSocial())
                .build();
        return this.lojaRepository.save(newLoja);
    }

}
