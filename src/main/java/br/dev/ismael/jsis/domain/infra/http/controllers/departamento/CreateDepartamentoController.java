package br.dev.ismael.jsis.domain.infra.http.controllers.departamento;

import br.dev.ismael.jsis.domain.application.cases.departamento.CreateDepartamentoUseCase;
import br.dev.ismael.jsis.domain.application.dto.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.infra.http.pipes.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Departamento")
@RestController
@RequestMapping("/departamento")
public class CreateDepartamentoController {
    @Autowired
    private CreateDepartamentoUseCase createDepartamentoUseCase;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Departamento criado com sucesso."),
            @ApiResponse(responseCode = "401", description = "Erro ao criar Departamento", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<Void> execute(@RequestBody @Valid DepartamentoRequestDTO departamentoRequestDTO){
        this.createDepartamentoUseCase.execute(departamentoRequestDTO);
        return ResponseEntity.status(201).build();
    }
}
