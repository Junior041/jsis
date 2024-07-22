package br.dev.ismael.jsis.domain.application.dto.etapa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class EtapaRequestDTO {
    @Schema(example = "Em espera")
    private String nome;

    @Schema(example = "Cliente em espera")
    private String descricao;

    @Schema(example ="1")
    private String prioridade;

    @Schema(example ="#FFFFF")
    private String corHexadecimal;

    @Schema(example = "1")
    private Integer fkDepartamento;
}
