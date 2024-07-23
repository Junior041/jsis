package br.dev.ismael.jsis.domain.application.dto.emailPessoa;

import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaResponseDTO;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailPessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class EmailPessoaResponseDTO {
    @Schema(example = "8cc9f52c-8b65-40ff-bb58-692f5adce00f")
    private UUID idEmailPessoa;
    @Schema(example = "teste@gmail.com")
    private String email;
    @Schema(example = "false")
    private Boolean recebeNovidades;
    @Schema(implementation = PessoaResponseDTO.class)
    private PessoaResponseDTO pessoa;

    public static EmailPessoaResponseDTO transformToDTO(EmailPessoa emailPessoa){
        return EmailPessoaResponseDTO.builder()
                .idEmailPessoa(emailPessoa.getIdEmailPessoa())
                .email(emailPessoa.getEmail())
                .recebeNovidades(emailPessoa.getRecebeNovidades())
                .pessoa(PessoaResponseDTO.transformToDTO(emailPessoa.getPessoa()))
                .build();
    }

}
