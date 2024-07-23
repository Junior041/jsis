package br.dev.ismael.jsis.domain.application.dto.telefonePessoa;

import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaResponseDTO;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TelefonePessoaResponseDTO {

    @Schema(example = "b448ae4d-ac4c-41fc-9607-5eade1d05c5e")
    private UUID idTelefonePessoa;

    @Schema(example = "99123-9364")
    private String telefone;

    @Schema(example = "47")
    private String ddd;

    @Schema(example = "true")
    private Boolean whatsapp;

    @Schema(implementation = PessoaResponseDTO.class)
    private PessoaResponseDTO pessoa;


    public static TelefonePessoaResponseDTO transformToDTO(TelefonePessoa telefonePessoa){
        return TelefonePessoaResponseDTO.builder()
                .idTelefonePessoa(telefonePessoa.getIdTelefonePessoa())
                .ddd(telefonePessoa.getDdd())
                .telefone(telefonePessoa.getTelefone())
                .whatsapp(telefonePessoa.getWhatsapp())
                .pessoa(PessoaResponseDTO.transformToDTO(telefonePessoa.getPessoa()))
                .build();
    }
}
