package br.dev.ismael.jsis.domain.application.errors;

public class WrongCredentialsError extends RuntimeException {
    public WrongCredentialsError() {
        super("Credenciais Invalidas.");
    }
}
