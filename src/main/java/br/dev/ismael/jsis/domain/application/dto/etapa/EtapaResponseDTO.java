package br.dev.ismael.jsis.domain.application.dto.etapa;

import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EtapaResponseDTO {
    @Schema(example = "1")
    private Integer idEtapa;

    @Schema(example = "Em espera")
    private String nome;

    @Schema(example = "Cliente em espera")
    private String descricao;

    @Schema(example ="1")
    private Integer prioridade;

    @Schema(example ="#FFFFF")
    private String corHexadecimal;

    @Schema(example = "Vendas")
    private String departamento;

    public static EtapaResponseDTO transformToDTO(Etapa etapa){
        return EtapaResponseDTO.builder()
                .idEtapa(etapa.getIdEtapa())
                .departamento(etapa.getDepartamento().getTitulo())
                .nome(etapa.getNome())
                .descricao(etapa.getDescricao())
                .corHexadecimal(etapa.getCorHexadecimal())
                .prioridade(etapa.getPrioridade())
                .build();
    }
}
