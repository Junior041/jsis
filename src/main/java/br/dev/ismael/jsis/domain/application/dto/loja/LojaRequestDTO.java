package br.dev.ismael.jsis.domain.application.dto.loja;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LojaRequestDTO {

    @Schema(example = "Junior .INC")
    private String razaoSocial;

    @Schema(example = "00000000000")
    private String cpfCnpj;

    @Schema(example = "Junior")
    private String nomeResponsavel;
}
