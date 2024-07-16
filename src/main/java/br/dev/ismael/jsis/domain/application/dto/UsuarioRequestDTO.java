package br.dev.ismael.jsis.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO {
    @Schema(example = "teste@gmail.com")
    private String email;
    @Schema(example = "senha")
    private String senha;
    @Schema(example = "2")
    private Integer fkDepartamento;
    @Schema(example = "UUID")
    private UUID fkLoja;
}
