package br.dev.ismael.jsis.domain.infra.security;

import br.dev.ismael.jsis.domain.infra.http.pipes.dto.ErrorResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.message = "Acesso negado. Você precisa estar autenticado para acessar este recurso.";
        errorResponseDTO.status = HttpStatus.UNAUTHORIZED.value();
        errorResponseDTO.setTimestamp(new Timestamp(new Date().getTime()));

        ObjectMapper objectMapper = new ObjectMapper();
        response.getOutputStream().println(objectMapper.writeValueAsString(errorResponseDTO));
    }
}
