package br.dev.ismael.jsis.domain.application.cases.departamento;

import br.dev.ismael.jsis.domain.application.dto.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartamentoUseCase {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    public Departamento execute(DepartamentoRequestDTO departamentoRequestDTO){

        Loja loja = this.lojaRepository.findById(departamentoRequestDTO.getFkLoja()).orElseThrow(() -> new DadoNaoEncontradoErro());

        Departamento newDepartamento = Departamento.builder()
                .icone(departamentoRequestDTO.getIcone())
                .nome(departamentoRequestDTO.getNome())
                .titulo(departamentoRequestDTO.getTitulo())
                .loja(loja)
                .build();
        return this.departamentoRepository.save(newDepartamento);
    }
}
