package br.dev.ismael.jsis.domain.infra.http.controllers.email;

import br.dev.ismael.jsis.domain.application.cases.emailPessoa.CreateEmailPessoaUseCae;
import br.dev.ismael.jsis.domain.application.dto.emailPessoa.EmailPessoaRequestDTO;
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
@RequestMapping("/email/pessoa")
@Tag(name = "Email")
public class CreateEmailPessoaController {

    @Autowired
    private CreateEmailPessoaUseCae createEmailPessoaUseCae;

    @PostMapping
    @ApiResponse(
            responseCode = "201",
            description = "Email da pessoa criado com sucesso."
    )
    public ResponseEntity<Void> execute(
            @Valid
            @RequestBody
            EmailPessoaRequestDTO
            emailPessoaRequestDTO
    ){
        this.createEmailPessoaUseCae.execute(emailPessoaRequestDTO);
        return ResponseEntity.status(201).build();
    }

}
