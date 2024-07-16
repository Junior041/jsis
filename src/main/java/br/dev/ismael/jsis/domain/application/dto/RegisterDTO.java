package br.dev.ismael.jsis.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class RegisterDTO {
    @Schema(example = "teste@gmail.com")
    private String email;

    @Schema(example = "password")
    private String senha;

    @Schema(example = "admin")
    private String role;

    @Schema(example = "UUID")
    private UUID fkLoja;

    @Schema(example = "2")
    private Integer fkDepartamento;
}
