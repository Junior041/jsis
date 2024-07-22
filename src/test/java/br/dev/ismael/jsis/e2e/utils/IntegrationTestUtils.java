package br.dev.ismael.jsis.e2e.utils;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.UserRoles;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
@Component
public class IntegrationTestUtils {

        @Autowired
        private LojaRepository lojaRepository;
        @Autowired
        private DepartamentoRepository departamentoRepository;
        @Autowired
        private UsuarioRepository usuarioRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired

        private Encrypter encrypter;
    public IntegrationTestUtils(LojaRepository lojaRepository, DepartamentoRepository departamentoRepository,
                                UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, Encrypter encrypter) {
        this.lojaRepository = lojaRepository;
        this.departamentoRepository = departamentoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.encrypter = encrypter;
    }

    public String createAndGetTokenForUser(String email, String senha, Loja loja, Departamento departamento, UserRoles role) throws Exception {
        // Criar usuário
        Usuario usuario = this.usuarioRepository.saveAndFlush(Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .departamento(departamento)
                .email(email)
                .senha(this.passwordEncoder.encode(senha))
                .loja(loja)
                .role(role)
                .build());

        // Gerar token JWT
        return this.encrypter.encrypt(usuario);
    }

    public Loja createAndSaveLoja(String cpfCnpj, String nomeResponsavel) {
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj(cpfCnpj)
                .nomeResponsavel(nomeResponsavel)
                .createdAt(LocalDateTime.now())
                .build();
        return this.lojaRepository.saveAndFlush(loja);
    }

    public Departamento createAndSaveDepartamento(Loja loja, String nome, String titulo, String icone) {
        Departamento departamento = Departamento.builder()
                .idDepartamento(1) // Ajuste conforme necessário para a geração do ID
                .loja(loja)
                .nome(nome)
                .titulo(titulo)
                .icone(icone)
                .build();
        return this.departamentoRepository.saveAndFlush(departamento);
    }
}
