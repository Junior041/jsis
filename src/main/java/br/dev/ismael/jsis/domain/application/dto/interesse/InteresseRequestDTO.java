package br.dev.ismael.jsis.domain.application.dto.interesse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InteresseRequestDTO {
    @Schema(example = "Casa")
    private String titulo;

    @Schema(example = "Cliente que uma casa de 400 mil reais.")
    private String descricao;
}
