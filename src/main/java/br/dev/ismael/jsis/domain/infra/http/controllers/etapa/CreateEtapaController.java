package br.dev.ismael.jsis.domain.infra.http.controllers.etapa;

import br.dev.ismael.jsis.domain.application.cases.etapa.CreateEtapaUseCase;
import br.dev.ismael.jsis.domain.application.dto.etapa.EtapaRequestDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etapa")
@Tag(name = "Etapa")
public class CreateEtapaController {

    @Autowired
    private CreateEtapaUseCase createEtapaUseCase;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Etapa Criada com sucesso."
    )
    public ResponseEntity<Void> execute(
            @Valid
            @RequestBody
            EtapaRequestDTO etapaRequestDTO
    ){
        this.createEtapaUseCase.execute(etapaRequestDTO);
        return ResponseEntity.status(201).build();
    }
}
