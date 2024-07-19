package br.dev.ismael.jsis.domain.infra.http.controllers.auth;

import br.dev.ismael.jsis.domain.application.cases.usuario.LoginUseCase;
import br.dev.ismael.jsis.domain.application.dto.AuthenticationDTO;
import br.dev.ismael.jsis.domain.infra.http.pipes.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthenticationController {
    @Autowired
    private LoginUseCase loginUseCase;
    @PostMapping("/login")
    @Operation(description = "Requisição utilizada para retornar o JWT utilizado para autenticação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(name = "JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c", description = "JWT utilizado para comunicação com a api."))),
            @ApiResponse(responseCode = "401", description = "Acesso negado!", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) throws Exception {
        String token = this.loginUseCase.execute(authenticationDTO.getEmail(),authenticationDTO.getSenha());
        return ResponseEntity.ok(token);
    }

}
