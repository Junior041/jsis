package br.dev.ismael.jsis.domain.application.cases.departamento;

import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FetchDepartamentoByLojaUseCase {
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoResponseDTO> execute(String lojaId){
        List<Departamento> departamentos = this.departamentoRepository.findByLojaIdLoja(UUID.fromString(lojaId));
        return departamentos.stream().map(DepartamentoResponseDTO::transformToDTO).toList();
    }
}
