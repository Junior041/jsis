package br.dev.ismael.jsis.domain.application.errors;

public class JaCadastradoErro extends RuntimeException {
    public JaCadastradoErro() {
        super("Dado já cadastrado.");
    }
}
