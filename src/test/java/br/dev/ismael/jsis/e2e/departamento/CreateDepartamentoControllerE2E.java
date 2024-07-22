package br.dev.ismael.jsis.e2e.departamento;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.e2e.utils.IntegrationTestUtils;
import br.dev.ismael.jsis.e2e.utils.TestUtils;
import jakarta.transaction.Transactional;
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

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class CreateDepartamentoControllerE2E {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    private IntegrationTestUtils testUtils;

    @BeforeEach
    public void setup() {
        // Inicializa o MockMvc
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        // Injeta as dependências em IntegrationTestUtils
        testUtils = new IntegrationTestUtils(
                context.getBean(LojaRepository.class),
                context.getBean(DepartamentoRepository.class),
                context.getBean(UsuarioRepository.class),
                context.getBean(PasswordEncoder.class),
                context.getBean(Encrypter.class)
        );
    }

    @Test
    public void test_sucesso() throws Exception {
        Map<String, Object> tokenAndIdLoja = testUtils.createAndGetTokenForUser();

        String token = (String) tokenAndIdLoja.get("token");
        UUID lojaId = (UUID) tokenAndIdLoja.get("idLoja");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/departamento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJSON(
                        DepartamentoRequestDTO.builder()
                                .fkLoja(lojaId)
                                .icone("icone.png")
                                .nome("Vendas")
                                .titulo("Vendas")
                                .build()
                ))
                .header("Authorization", token)
        ).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        Boolean departamentoInDatabase = this.departamentoRepository.findByTituloAndLojaIdLoja("Vendas", lojaId).isPresent();
        assertThat(departamentoInDatabase).isTrue();
    }
}
