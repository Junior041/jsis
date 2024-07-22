package br.dev.ismael.jsis.domain.application.dto.origem;

import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrigemResponseDTO {
    @Schema(example = "1")
    private Integer idOrigem;

    @Schema(example = "Whatsapp")
    private String nome;

    @Schema(example = "Clientes vindos pelo whatsapp.")
    private String descricao;

    public static OrigemResponseDTO transformaToDTO(Origem origem){
        return OrigemResponseDTO.builder()
                .idOrigem(origem.getIdOrigem())
                .nome(origem.getNome())
                .descricao(origem.getDescricao())
                .build();
    }
}
