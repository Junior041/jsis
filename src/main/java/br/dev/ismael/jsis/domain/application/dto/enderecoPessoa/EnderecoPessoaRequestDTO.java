package br.dev.ismael.jsis.domain.application.dto.enderecoPessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EnderecoPessoaRequestDTO {
    @Schema(example = "888888-88")
    private String cep;
    @Schema(example = "Avenida")
    private String logradouro;
    @Schema(example = "Brusque")
    private String cidade;
    @Schema(example = "Santa Catarina")
    private String estado;
    @Schema(example = "Otto Renaux")
    private String rua;
    @Schema(example = "Pero do river mal")
    private String complemento;
    @Schema(example = "41ed1126-b04b-4992-bf7c-ec552d45b972")
    private UUID fkPessoa;
}
