package br.dev.ismael.jsis.cases.emailLoja;

import br.dev.ismael.jsis.domain.application.cases.emailLoja.CreateEmailLojaUseCase;
import br.dev.ismael.jsis.domain.application.dto.emailLoja.EmailLojaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EmailLojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailLoja;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEmailLojaUseCaseTest {
    @Mock
    private LojaRepository lojaRepository;

    @Mock
    private EmailLojaRepository emailLojaRepository;

    @InjectMocks
    private CreateEmailLojaUseCase createEmailLojaUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar o email de uma loja.")
    public void test_sucesso(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsavel")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        EmailLoja emailLoja = EmailLoja.builder()
                .loja(loja)
                .email("teste@gmail.com")
                .titulo("Email de marketing")
                .recebeLeads(true)
                .createdAt(LocalDateTime.now())
                .build();

        when(this.lojaRepository.findById(any())).thenReturn(Optional.of(loja));
        when(this.emailLojaRepository.save(any())).thenReturn(emailLoja);

        var result = this.createEmailLojaUseCase.execute(
                EmailLojaRequestDTO.builder()
                        .fkLoja(loja.getIdLoja())
                        .email(emailLoja.getEmail())
                        .titulo(emailLoja.getTitulo())
                        .recebeLeads(emailLoja.getRecebeLeads())
                        .build()
        );

        assertThat(result).isNotNull();
        assertThat(result.getEmail()).isEqualTo(emailLoja.getEmail());

    }
    @Test
    @DisplayName("não deve ser possível cadastrar o email de uma loja que não existe.")
    public void test_erro_loja_nao_encontra(){
        EmailLoja emailLoja = EmailLoja.builder()
                .email("teste@gmail.com")
                .titulo("Email de marketing")
                .recebeLeads(true)
                .createdAt(LocalDateTime.now())
                .build();

        when(this.lojaRepository.findById(any())).thenReturn(Optional.empty());

        try {
            this.createEmailLojaUseCase.execute(
                    EmailLojaRequestDTO.builder()
                            .fkLoja(UUID.randomUUID())
                            .email(emailLoja.getEmail())
                            .titulo(emailLoja.getTitulo())
                            .recebeLeads(emailLoja.getRecebeLeads())
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }
}
