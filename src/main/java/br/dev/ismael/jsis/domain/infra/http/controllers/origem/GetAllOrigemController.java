package br.dev.ismael.jsis.domain.infra.http.controllers.origem;

import br.dev.ismael.jsis.domain.application.cases.origem.GetAllOrigemUseCase;
import br.dev.ismael.jsis.domain.application.dto.origem.OrigemResponseDTO;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/origem")
@Tag(name = "Origem")
public class GetAllOrigemController {

    @Autowired
    private GetAllOrigemUseCase getAllOrigemUseCase;

    @ApiResponse(
            responseCode = "200",
            description = "Retorna as origens que estão cadastradas no sistema.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = OrigemResponseDTO.class)))
    )
    @GetMapping()
    public ResponseEntity<List<OrigemResponseDTO>> execute() {
        List<OrigemResponseDTO> origemResponseDTOS = this.getAllOrigemUseCase.execute();
        return ResponseEntity.ok(origemResponseDTOS);
    }
}
