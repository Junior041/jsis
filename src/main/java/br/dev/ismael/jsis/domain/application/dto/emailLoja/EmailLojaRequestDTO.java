package br.dev.ismael.jsis.domain.application.dto.emailLoja;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EmailLojaRequestDTO {
    @Schema(example = "Email Marketing")
    private String titulo;
    @Schema(example = "teste@gmail.com")
    private String email;
    @Schema(example = "true")
    private Boolean recebeLeads;
    @Schema(example = "44014ecf-7b0a-4614-a10c-864b4ae30e42")
    private UUID fkLoja;
}
