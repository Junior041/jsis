package br.dev.ismael.jsis.domain.infra.http.controllers.departamento;

import br.dev.ismael.jsis.domain.application.cases.departamento.FetchDepartamentoByLojaUseCase;
import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamento")
@Tag(name = "Departamento")
public class FetchDepartamentoByLojaController {
    @Autowired
    private FetchDepartamentoByLojaUseCase fetchDepartamentoByLojaUseCase;

    @GetMapping("/{idLoja}")
    @ApiResponse(
            responseCode = "200",
            description = "Retorna os departamentos que estão cadastrados na loja.",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = DepartamentoResponseDTO.class)))
    )
    @Parameter(
            name = "idLoja",
            description = "ID da loja",
            required = true,
            example = "ed92489e-c71d-4151-a48f-189b7bed089f",
            schema = @Schema(type = "string")
    )
    public ResponseEntity<List<DepartamentoResponseDTO>> execute(
            @Valid @PathVariable String idLoja
    ){
        List<DepartamentoResponseDTO> departamentoResponseDTOS = this.fetchDepartamentoByLojaUseCase.execute(
                idLoja
        );

        return ResponseEntity.ok(departamentoResponseDTOS);

    }

}
