package br.dev.ismael.jsis.domain.application.dto.emailPessoa;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EmailPessoaRequestDTO {
    @Schema(example = "teste@gmail.com")
    private String email;
    @Schema(example = "false")
    private Boolean recebeNovidades;
    @Schema(example = "3767026c-fd2f-4b14-afc1-414e26f965cf")
    private UUID fkPessoa;
}
