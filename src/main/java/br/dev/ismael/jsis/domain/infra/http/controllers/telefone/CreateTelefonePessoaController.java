package br.dev.ismael.jsis.domain.infra.http.controllers.telefone;

import br.dev.ismael.jsis.domain.application.cases.telefonePessoa.CreateTelefonePessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaRequestDTO;
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
@RequestMapping("/telefone/pessoa")
@Tag(name = "Telefone")
public class CreateTelefonePessoaController {

    @Autowired
    private CreateTelefonePessoaUseCase createTelefonePessoaUseCase;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Telefone da pessoa criado com sucesso."
    )
    public ResponseEntity<Void> execute(
            @Valid
            @RequestBody
            TelefonePessoaRequestDTO
            telefonePessoaRequestDTO
    ){
        this.createTelefonePessoaUseCase.execute(telefonePessoaRequestDTO);
        return ResponseEntity.status(201).build();
    }

}
