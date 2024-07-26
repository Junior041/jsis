package br.dev.ismael.jsis.domain.infra.http.controllers.pessoa;

import br.dev.ismael.jsis.domain.application.cases.pessoa.CreatePessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaRequestDTO;
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
@RequestMapping("/pessoa")
@Tag(name = "Pessoa")
public class CreatePessoaController {

    @Autowired
    private CreatePessoaUseCase createPessoaUseCase;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Pessoa criada com sucesso."
    )
    public ResponseEntity<Void> execute(
            @RequestBody
            @Valid
            PessoaRequestDTO pessoaRequestDTO
    ){
        this.createPessoaUseCase.execute(pessoaRequestDTO);
        return ResponseEntity.status(201).build();
    }

}
