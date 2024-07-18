package br.dev.ismael.jsis.domain.infra.http.pipes;

import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.errors.WrongCredentialsError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        HttpStatus status = determineHttpStatus(ex); // Método para determinar o status HTTP baseado na exceção
        String message = ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred";

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                message,
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    // Método para determinar o status HTTP baseado na exceção
    private HttpStatus determineHttpStatus(Exception ex) {
        // Aqui você pode adicionar lógica para retornar diferentes status HTTP com base no tipo de exceção
        if (ex instanceof DadoNaoEncontradoErro || ex instanceof JaCadastradoErro ) {
            return HttpStatus.BAD_REQUEST;
        }else if (ex instanceof AccessDeniedException) {
            return HttpStatus.FORBIDDEN;
        } else if (ex instanceof WrongCredentialsError) {
            return HttpStatus.UNAUTHORIZED;
        }else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}