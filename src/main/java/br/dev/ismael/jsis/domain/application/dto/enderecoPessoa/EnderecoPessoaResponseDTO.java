package br.dev.ismael.jsis.domain.application.dto.enderecoPessoa;

import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaResponseDTO;
import br.dev.ismael.jsis.domain.enterprise.entities.EnderecoPessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EnderecoPessoaResponseDTO {
    @Schema(example = "d1e16ee5-3758-4149-81c3-48e507a81b77")
    private UUID idEnderecoPessoa;
    @Schema(example = "888888-88")
    private String cep;
    @Schema(example = "Avenida")
    private String logradouro;
    @Schema(example = "Brusque")
    private String cidade;
    @Schema(example = "Santa Catarina")
    private String estado;
    @Schema(example = "Otto Renaux")
    private String rua;
    @Schema(example = "Pero do river mal")
    private String complemento;
    @Schema(implementation = PessoaResponseDTO.class)
    private PessoaResponseDTO pessoa;

    public static EnderecoPessoaResponseDTO transformToDTO(EnderecoPessoa enderecoPessoa) {
        return EnderecoPessoaResponseDTO.builder()
                .idEnderecoPessoa(enderecoPessoa.getIdEnderecoPessoa())
                .cep(enderecoPessoa.getCep())
                .logradouro(enderecoPessoa.getLogradouro())
                .cidade(enderecoPessoa.getCidade())
                .estado(enderecoPessoa.getEstado())
                .rua(enderecoPessoa.getRua())
                .complemento(enderecoPessoa.getComplemento())
                .pessoa(PessoaResponseDTO.transformToDTO(enderecoPessoa.getPessoa()))
                .build();
    }
}
