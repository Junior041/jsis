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
import java.util.HashMap;
import java.util.Map;
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

    public Map<String, Object> createAndGetTokenForUser() throws Exception {
        Map<String, Object> result = new HashMap<>();
        Loja loja = this.createAndSaveLoja();
        Departamento departamento = this.createAndSaveDepartamento(loja);
        Usuario usuario = this.usuarioRepository.saveAndFlush(Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .departamento(departamento)
                .email("teste@gmail.com")
                .senha(this.passwordEncoder.encode("senha"))
                .loja(loja)
                .role(UserRoles.ADMIN)
                .build());

        // Gerar token JWT
        result.put("token", this.encrypter.encrypt(usuario));
        result.put("idLoja", loja.getIdLoja());
        return result;
    }

    private Loja createAndSaveLoja() {
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("00000000")
                .nomeResponsavel("Ismael")
                .razaoSocial("Ismael LTDA")
                .createdAt(LocalDateTime.now())
                .build();
        return this.lojaRepository.saveAndFlush(loja);
    }

    private Departamento createAndSaveDepartamento(Loja loja) {
        Departamento departamento = Departamento.builder()
                .idDepartamento(1) // Ajuste conforme necessário para a geração do ID
                .loja(loja)
                .nome("Diretoria")
                .titulo("Diretoria")
                .icone("diretoria.png")
                .build();
        return this.departamentoRepository.saveAndFlush(departamento);
    }
}
