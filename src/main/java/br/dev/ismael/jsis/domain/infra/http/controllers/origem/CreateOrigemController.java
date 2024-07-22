package br.dev.ismael.jsis.domain.infra.http.controllers.origem;

import br.dev.ismael.jsis.domain.application.cases.origem.CreateOrigemUseCase;
import br.dev.ismael.jsis.domain.application.dto.origem.OrigemRequestDTO;
import br.dev.ismael.jsis.domain.infra.http.pipes.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/origem")
@Tag(name = "Origem")
public class CreateOrigemController {

    @Autowired
    private CreateOrigemUseCase createOrigemUseCase;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Departamento criado com sucesso."),
            @ApiResponse(responseCode = "401", description = "Erro ao criar Departamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<Void> execute(@RequestBody @Valid OrigemRequestDTO origemRequestDTO){
        this.createOrigemUseCase.execute(origemRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
