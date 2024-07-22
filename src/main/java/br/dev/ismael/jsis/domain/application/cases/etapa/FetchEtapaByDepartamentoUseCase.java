package br.dev.ismael.jsis.domain.application.cases.etapa;

import br.dev.ismael.jsis.domain.application.dto.etapa.EtapaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchEtapaByDepartamentoUseCase {
    @Autowired
    private EtapaRepository etapaRepository;

    public List<EtapaResponseDTO> execute(Integer fkDepartamento){
        List<Etapa> etapas = this.etapaRepository.findByDepartamentoIdDepartamento(fkDepartamento);
        return etapas.stream().map(EtapaResponseDTO::transformToDTO).toList();
    }
}
