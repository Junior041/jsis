package br.dev.ismael.jsis.domain.infra.http.pipes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ErrorResponseDTO {
    @Schema(example = "401", description = "Status de retorno da requisição.")
    public Number status;

    @Schema(example = "Credenciais Invalidas.", description = "Mensagem de erro da requisição.")
    public String message;

    @Schema(example = "1721333339098", description = "Timestamp da requisição.")
    public Date timestamp;
}
