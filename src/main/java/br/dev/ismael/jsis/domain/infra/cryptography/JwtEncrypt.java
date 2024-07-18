package br.dev.ismael.jsis.domain.infra.cryptography;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Service
public class JwtEncrypt extends Encrypter {
    @Override
    public String encrypt(Usuario payload) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256("KeyProvisoria");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("SIG")
                .withClaim("sub",payload.getIdUsuario().toString())
                .withClaim("idLoja",payload.getLoja().getIdLoja().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return token;
    }
}
