package br.dev.ismael.jsis.domain.application.dto.departamento;

import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class DepartamentoResponseDTO {
    @Schema(example = "1")
    private Integer idDepartamento;

    @Schema(example = "Vendas")
    private String nome;

    @Schema(example = "Vendas")
    private String titulo;

    @Schema(example = " b43a65e0-3c79-485c-848b-8319159f3ebd .webp")
    private String icone;

    @Schema(example = " b43a65e0-3c79-485c-848b-8319159f3ebd ")
    private UUID fkLoja;

    public static DepartamentoResponseDTO transformToDTO(Departamento departamento){
        return DepartamentoResponseDTO.builder()
                .idDepartamento(departamento.getIdDepartamento())
                .nome(departamento.getNome())
                .titulo(departamento.getTitulo())
                .icone(departamento.getIcone())
                .fkLoja(departamento.getLoja().getIdLoja())
                .build();
    }
}
