package br.dev.ismael.jsis.e2e.utils;

import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class TestUtils {

    public static String objectToJSON(Object obj){
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String createJWT(Usuario payload) throws Exception {
        Algorithm algorithm = Algorithm.HMAC256("KeyProvisoria");
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("JSIS")
                .withClaim("sub",payload.getIdUsuario().toString())
                .withClaim("idLoja",payload.getLoja().getIdLoja().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return token;
    }

}
