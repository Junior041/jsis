package br.dev.ismael.jsis.e2e.departamento;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.UserRoles;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import br.dev.ismael.jsis.e2e.utils.TestUtils;
import jakarta.transaction.Transactional;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class CreteDepartamentoControllerE2E {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
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

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }


    @Test
    @Description("Deve ser possível criar um departamento.")
    public void test_sucesso() throws Exception {
        Loja loja = lojaRepository.saveAndFlush(Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("00000050000")
                .nomeResponsavel("Ismael Junior")
                .createdAt(LocalDateTime.now())
                .build());
        Departamento departamento = departamentoRepository.saveAndFlush(Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("Programação")
                .titulo("Programação")
                .icone("icone.png")
                .build());
        Usuario usuario = this.usuarioRepository.saveAndFlush(Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .departamento(departamento)
                .email("teste@gamil.com")
                .senha(this.passwordEncoder.encode("senha"))
                .loja(loja)
                .role(UserRoles.ADMIN)
                .build());

        var token = this.encrypter.encrypt(usuario);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/departamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJSON(
                        DepartamentoRequestDTO.builder()
                                .fkLoja(loja.getIdLoja())
                                .icone("icone.png")
                                .nome("Vendas")
                                .titulo("Vendas")
                                .build()
                ))
                .header("Authorization", token)
        ).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Boolean departamentoInDatabase = this.departamentoRepository.findByTituloAndLojaIdLoja("Programação",loja.getIdLoja()).isPresent();
        assertThat(departamentoInDatabase).isTrue();
    }
}
