package br.dev.ismael.jsis.domain.application.dto.telefonePessoa;

import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TelefonePessoaRequestDTO {
    @Schema(example = "99123-9364")
    private String telefone;
    @Schema(example = "47")
    private String ddd;
    @Schema(example = "true")
    private Boolean whatsapp;
    @Schema(example = "534576b2-bda2-4d69-9055-eef6471dad20")
    private UUID fkPessoa;
}
