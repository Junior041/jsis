package br.dev.ismael.jsis.domain.application.cryptography;

import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;

import java.util.Map;

abstract public class Encrypter {
    public abstract String encrypt(Usuario payload) throws Exception;
}
