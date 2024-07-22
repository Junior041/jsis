package br.dev.ismael.jsis.cases.loja;

import br.dev.ismael.jsis.domain.application.cases.loja.CreateLojaUseCase;
import br.dev.ismael.jsis.domain.application.dto.loja.LojaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
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
public class CreateLojaUseCaseTest {
    @Mock
    private LojaRepository lojaRepository;

    @InjectMocks
    private CreateLojaUseCase sut;

    @Test
    @DisplayName("Deve ser possível cadastrar ")
    public void test_sucesso(){
        when(this.lojaRepository.save(any(Loja.class))).thenReturn(
                Loja.builder()
                        .idLoja(UUID.randomUUID())
                        .cpfCnpj("000.000.000-00")
                        .nomeResponsavel("Teste Responsavel")
                        .razaoSocial("teste SA")
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        var result = this.sut.execute(
                LojaRequestDTO.builder()

                        .cpfCnpj("000.000.000-00")
                        .nomeResponsavel("Teste Responsavel")
                        .razaoSocial("teste SA")
                        .build()
        );
        assertThat(result).hasFieldOrProperty("idLoja");
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Loja.class);
    }

    @Test
    @DisplayName("Nao deve ser possível cadastrar loja com cpfCnpj repetido")
    public void test_cpf_cnpj_ja_cadastrado(){
        when(this.lojaRepository.findByCpfCnpj(any(String.class))).thenReturn(
                Optional.of(
                        Loja.builder()
                        .idLoja(UUID.randomUUID())
                        .cpfCnpj("000.000.000-00")
                        .nomeResponsavel("Teste Responsavel")
                        .razaoSocial("teste SA")
                        .createdAt(LocalDateTime.now())
                        .build()
                )
        );

        try {
            this.sut.execute(
                    LojaRequestDTO.builder()
                            .cpfCnpj("000.000.000-00")
                            .nomeResponsavel("Teste Responsavel")
                            .razaoSocial("teste SA")
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(JaCadastradoErro.class);
        }
    }
}
