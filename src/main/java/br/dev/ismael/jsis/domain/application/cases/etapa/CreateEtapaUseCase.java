package br.dev.ismael.jsis.domain.application.cases.etapa;

import br.dev.ismael.jsis.domain.application.dto.etapa.EtapaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEtapaUseCase {
    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    private final Etapa etapa = new Etapa();

    public Etapa execute(EtapaRequestDTO etapaRequestDTO){
        Departamento departamento = this.departamentoRepository.findById(etapaRequestDTO.getFkDepartamento()).orElseThrow(DadoNaoEncontradoErro::new);
        this.etapa.setDepartamento(departamento);
        this.etapa.setPrioridade(etapaRequestDTO.getPrioridade());
        this.etapa.setNome(etapaRequestDTO.getNome());
        this.etapa.setCorHexadecimal(etapaRequestDTO.getCorHexadecimal());
        this.etapa.setDescricao(etapaRequestDTO.getDescricao());
        this.ajustaOrdemDaPrioridade();
        return this.etapaRepository.save(this.etapa);
    }

    private void ajustaOrdemDaPrioridade(){
        boolean prioridadeAlreadyExists = this.etapaRepository.findIfPrioridadeAlreadyExists(this.etapa.getDepartamento().getIdDepartamento(), this.etapa.getPrioridade());
        Integer maiorPrioridade = this.etapaRepository.findMaxPrioridadeByDepartamentoId(this.etapa.getDepartamento().getIdDepartamento());
        this.etapa.setPrioridade(prioridadeAlreadyExists ? maiorPrioridade + 1 : this.etapa.getPrioridade());
    }
}
