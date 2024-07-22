package br.dev.ismael.jsis.domain.application.dto.loja;

import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@Builder
public class LojaResponseDTO {
    @Schema(example = " b43a65e0-3c79-485c-848b-8319159f3ebd ")
    private UUID idLoja;

    @Schema(example = "Junior .INC")
    private String razaoSocial;

    @Schema(example = "00000000000")
    private String cpfCnpj;

    @Schema(example = "Junior")
    private String nomeResponsavel;


    @Schema(example = "Junior")
    private LocalDateTime createdAt;

    public static LojaResponseDTO transformToDTO(Loja loja){
        return LojaResponseDTO.builder()
                .idLoja(loja.getIdLoja())
                .razaoSocial(loja.getRazaoSocial())
                .cpfCnpj(loja.getCpfCnpj())
                .nomeResponsavel(loja.getNomeResponsavel())
                .createdAt(loja.getCreatedAt())
                .build();
    }
}
