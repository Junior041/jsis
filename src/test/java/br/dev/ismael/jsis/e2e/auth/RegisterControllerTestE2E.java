package br.dev.ismael.jsis.e2e.auth;

import br.dev.ismael.jsis.domain.application.dto.UsuarioRequestDTO;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class RegisterControllerTestE2E {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @Description("Deve ser possível cadastrar um usuario no sistema")
    public void test_sucesso() throws Exception {
        // Criar e persistir a Loja

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
        Usuario usuario = Usuario.builder()
                .email("teste@gmail.com")
                .departamento(departamento)
                .role(UserRoles.ADMIN)
                .senha("senha")
                .loja(loja)
                .build();
        this.usuarioRepository.saveAndFlush(usuario);
        UsuarioRequestDTO usuarioRequestDTO = new UsuarioRequestDTO();

        BeanUtils.copyProperties(usuario, usuarioRequestDTO);
        usuarioRequestDTO.setEmail("teste2@gmail.com");
        usuarioRequestDTO.setFkLoja(loja.getIdLoja());
        usuarioRequestDTO.setFkDepartamento(departamento.getIdDepartamento());
        var result = mvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJSON(usuarioRequestDTO))
                        .header("Authorization",new TestUtils().createJWT(usuario)));
        result.andExpect(MockMvcResultMatchers.status().isOk());
        Boolean usuarioInDatabase = this.usuarioRepository.findByEmail(usuarioRequestDTO.getEmail()).isPresent();
        assertThat(usuarioInDatabase).isTrue();
    }
}
