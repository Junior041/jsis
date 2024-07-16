package br.dev.ismael.jsis.domain.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class DepartamentoRequestDTO {

    @Schema(example = "Vendas")
    private String nome;

    @Schema(example = "Vendas")
    private String titulo;

    @Schema(example = " b43a65e0-3c79-485c-848b-8319159f3ebd .webp")
    private String icone;

    @Schema(example = " b43a65e0-3c79-485c-848b-8319159f3ebd ")
    private UUID fkLoja;
}
