package br.dev.ismael.jsis.domain.application.dto.origem;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrigemRequestDTO {
    @Schema(example = "Whatsapp")
    private String nome;

    @Schema(example = "Clientes vindos pelo whatsapp.")
    private String descricao;
}
