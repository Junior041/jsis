package br.dev.ismael.jsis.domain.infra.http.controllers.endereco;

import br.dev.ismael.jsis.domain.application.cases.enderecoPessoa.CreateEnderecoPessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.enderecoPessoa.EnderecoPessoaRequestDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/endereco/pessoa")
@RestController
@Tag(name = "Endereco")
public class CreateEnderecoPessoaController {

    @Autowired
    private CreateEnderecoPessoaUseCase createEnderecoPessoaUseCase;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Endereco da pessoa criado com sucesso."
    )
    public ResponseEntity<Void> execute(
            @Valid
            @RequestBody
            EnderecoPessoaRequestDTO
            enderecoPessoaRequestDTO
    ){
        this.createEnderecoPessoaUseCase.execute(enderecoPessoaRequestDTO);
        return ResponseEntity.status(201).build();
    }

}
