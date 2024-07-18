package br.dev.ismael.jsis.domain.infra.http.controllers.auth;

import br.dev.ismael.jsis.domain.application.cases.usuario.CreateUsuarioUseCase;
import br.dev.ismael.jsis.domain.application.dto.RegisterDTO;
import br.dev.ismael.jsis.domain.application.dto.UsuarioRequestDTO;
import br.dev.ismael.jsis.domain.enterprise.entities.UserRoles;
import br.dev.ismael.jsis.domain.infra.http.pipes.dto.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class RegisterController {
    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;
    @PostMapping("/register")
    @Operation(description = "Requisição utilizada para cadastrar um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Dado não encontrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ja cadastrado.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "201", description = "Usuario cadastrado com sucesso.")
    }

    )
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO registerDTO){
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        BeanUtils.copyProperties(registerDTO, usuarioRequestDTO);
        usuarioRequestDTO.setRole(UserRoles.valueOf(registerDTO.getRole().toUpperCase()));
        this.createUsuarioUseCase.execute(usuarioRequestDTO);
        return ResponseEntity.status(200).build();
    }

}
