package br.dev.ismael.jsis.cases.origem;

import br.dev.ismael.jsis.domain.application.cases.origem.CreateOrigemUseCase;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateOrigemUseCaseTest {
    @Mock
    private OrigemRepository origemRepository;

    @InjectMocks
    private CreateOrigemUseCase createOrigemUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar uma origem.")
    public void test_successo(){
        when(this.origemRepository.save(any(Origem.class))).thenReturn(Origem.builder()
                        .nome("Whatsapp")
                        .descricao("Clientes vindos pelo Whatsapp.")
                .build());
        var result = this.createOrigemUseCase.execute("Whatsapp", "Clientes vindos pelo Whatsapp.");
        assertThat(result).hasFieldOrProperty("idOrigem");
        assertThat(result.getNome()).isEqualTo("Whatsapp");

    }
    @Test
    @DisplayName("Não deve ser possível cadastrar uma origem já existente.")
    public void test_ja_cadastrado(){
        when(this.origemRepository.findByNome(any(String.class))).thenReturn(
                Optional.of(Origem.builder()
                        .nome("Whatsapp")
                        .descricao("Clientes vindos pelo Whatsapp.")
                        .build())
        );
        try {
            this.createOrigemUseCase.execute("Whatsapp", "Clientes vindos pelo Whatsapp.");
        }catch (Exception e){
            assertThat(e).isInstanceOf(JaCadastradoErro.class);
        }
    }
}
