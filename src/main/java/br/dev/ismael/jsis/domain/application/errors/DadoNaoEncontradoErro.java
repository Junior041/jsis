package br.dev.ismael.jsis.domain.application.errors;

public class DadoNaoEncontradoErro extends RuntimeException {
    public DadoNaoEncontradoErro() {
        super("Dado não encontrado.");
    }
}
