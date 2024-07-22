package br.dev.ismael.jsis.domain.application.dto.pessoa;

import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PessoaResponseDTO {
    @Schema(example = "c07ddee6-ad5e-ffba-ad5e-b0221b998adc")
    private UUID idPessoa;
    @Schema(example = "Ismael")
    private String primeiroNome;
    @Schema(example = "Junior")
    private String ultimoNome;
    @Schema(example = "000.000.000-88")
    private String cpf;
    @Schema(example = "juninho")
    private String apelido;
    @Schema(example = "000000")
    private String rg;
    @Schema(example = "15/08/2002")
    private String dataNascimento;
    @Schema(example = "faf773e7-ffba-4b16-ad5e-b0221b998adc")
    private UUID cadastradoPorUsuarioId;
    @Schema(example = " c07ddee6-eead-4a18-8009-0ae0f89c27a9 ")
    private UUID fkLoja;

    public static PessoaResponseDTO transformToDTO(Pessoa pessoa){
        return PessoaResponseDTO.builder()
                .idPessoa(pessoa.getIdPessoa())
                .primeiroNome(pessoa.getPrimeiroNome())
                .ultimoNome(pessoa.getUltimoNome())
                .cpf(pessoa.getCpf())
                .apelido(pessoa.getApelido())
                .dataNascimento(pessoa.getDataNascimento())
                .cadastradoPorUsuarioId(pessoa.getUsuario().getIdUsuario())
                .fkLoja(pessoa.getLoja().getIdLoja())
                .build();
    }
}
