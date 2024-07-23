package br.dev.ismael.jsis.domain.application.dto.atendimentoEtapa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AtendimentoEtapaRequestDTO {
    @Schema(example = "Pegar verificar se o imóvel está disponível e retornar para o cliente.")
    private String descricao;
    @Schema(example = "1")
    private Integer fkAtendimento;
    @Schema(example = "1")
    private Integer fkEtapa;
    @Schema(example = "c0f11c26-bb28-4b32-b1cb-478bc68b60fc")
    private UUID fkUsuario;
}
