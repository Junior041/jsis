package br.dev.ismael.jsis.domain.application.cases.usuario;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.application.errors.WrongCredentialsError;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUseCase{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Encrypter encrypter;


    public String execute(String email, String senha) throws Exception {
        Usuario usuario = this.usuarioRepository.findByEmail(email).orElseThrow(() -> new WrongCredentialsError());
        Boolean passwordMatches = this.passwordEncoder.matches(senha, usuario.getPassword());
        if (!passwordMatches){
            throw new WrongCredentialsError();
        }

        String token = this.encrypter.encrypt(usuario);

        return token;
    }
}
