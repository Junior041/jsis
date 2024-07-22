package br.dev.ismael.jsis.e2e.origem;

import br.dev.ismael.jsis.domain.application.cryptography.Encrypter;
import br.dev.ismael.jsis.domain.application.dto.origem.OrigemRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.e2e.utils.IntegrationTestUtils;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class CreateOrigemControllerTestE2E {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    private IntegrationTestUtils testUtils;

    @Autowired
    private OrigemRepository origemRepository;

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
    @Description("Deve ser possível cadastrar uma origem.")
    public void test_sucesso() throws Exception {
        Map<String, Object> tokenAndIdLoja = testUtils.createAndGetTokenForUser();

        String token = (String) tokenAndIdLoja.get("token");

        var result = mvc.perform(MockMvcRequestBuilders.post("/origem")
                .content(TestUtils.objectToJSON(
                        OrigemRequestDTO.builder()
                                .nome("Whatsapp")
                                .descricao("Descrição")
                                .build()
                ))
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
        boolean origemInDatabase = this.origemRepository.findById(1).isPresent();
        assertThat(origemInDatabase).isTrue();
    }
}
