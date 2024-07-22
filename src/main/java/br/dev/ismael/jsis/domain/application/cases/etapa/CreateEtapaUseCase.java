package br.dev.ismael.jsis.domain.application.cases.etapa;

import br.dev.ismael.jsis.domain.application.dto.etapa.EtapaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateEtapaUseCase {
    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Etapa execute(EtapaRequestDTO etapaRequestDTO){
        Departamento departamento = this.departamentoRepository.findById(etapaRequestDTO.getFkDepartamento()).orElseThrow(() ->new DadoNaoEncontradoErro());
        Etapa etapa = new Etapa();
        BeanUtils.copyProperties(etapa, etapaRequestDTO);
        etapa.setDepartamento(departamento);
        return this.etapaRepository.save(etapa);
    }
}
