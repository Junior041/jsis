package br.dev.ismael.jsis.domain.infra.http.controllers.auth;

import br.dev.ismael.jsis.domain.application.cases.usuario.CreateUsuarioUseCase;
import br.dev.ismael.jsis.domain.application.dto.RegisterDTO;
import br.dev.ismael.jsis.domain.application.dto.UsuarioRequestDTO;
import br.dev.ismael.jsis.domain.enterprise.entities.UserRoles;
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
public class RegisterController {
    @Autowired
    private CreateUsuarioUseCase createUsuarioUseCase;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();
        BeanUtils.copyProperties(registerDTO, usuarioRequestDTO);
        usuarioRequestDTO.setRole(UserRoles.valueOf(registerDTO.getRole().toUpperCase()));
        this.createUsuarioUseCase.execute(usuarioRequestDTO);
        return ResponseEntity.status(200).build();
    }

}
