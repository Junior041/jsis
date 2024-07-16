package br.dev.ismael.jsis.domain.application.cases.departamento;

import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FetchDepartamentoByLojaUseCase {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> execute(String lojaId){
        return this.departamentoRepository.findByLojaIdLoja(UUID.fromString(lojaId));
    }
}
