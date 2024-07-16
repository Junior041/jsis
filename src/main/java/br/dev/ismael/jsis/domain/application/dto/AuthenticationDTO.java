package br.dev.ismael.jsis.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationDTO {
    @Schema(example = "teste@gmail.com")
    private String email;

    @Schema(example = "password")
    private String senha;
}
