package br.dev.ismael.jsis.domain.infra.http.controllers.interesse;

import br.dev.ismael.jsis.domain.application.cases.interesse.CreateInteresseUseCase;
import br.dev.ismael.jsis.domain.application.dto.interesse.InteresseRequestDTO;
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
@RequestMapping("/interesse")
@Tag(name = "Interesse")
public class CreateInteresseController {
    @Autowired
    private CreateInteresseUseCase createInteresseUseCase;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Interesse criado com sucesso."
    )
    public ResponseEntity<Void> execute(
            @Valid
            @RequestBody
            InteresseRequestDTO
            interesseRequestDTO
    ){
        this.createInteresseUseCase.execute(interesseRequestDTO);
        return ResponseEntity.status(201).build();
    }

}
