package br.dev.ismael.jsis.domain.application.dto.atendimento;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AtendimentoRequestDTO {
    @Schema(example = "Cliente que uma maquina de até 10mil reais para cortar arvores.")
    private String descricao;
    @Schema(example = "1")
    private Integer fkOrigem;
    @Schema(example = "1")
    private Integer fkInteresse;
    @Schema(example = "1cc3762e-2455-42f8-9896-9e12be5d5f9d")
    private UUID fkPessoa;
    @Schema(example = "ea9f7977-7bf0-49a6-b9fe-8cf8ed983050")
    private UUID fkLoja;
}
