package br.dev.ismael.jsis.cases.pessoa;

import br.dev.ismael.jsis.domain.application.cases.pessoa.CreatePessoaUseCase;
import br.dev.ismael.jsis.domain.application.cases.pessoa.FetchPessoaByLojaUseCase;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchPessoaByLojaUseCaseTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private FetchPessoaByLojaUseCase fetchPessoaByLojaUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de PessoaResponseDTO")
    public void test_sucesso() {
        when(this.pessoaRepository.findPessoaByLojaIdLoja(any())).thenReturn(List.of(
                Pessoa.builder()
                        .idPessoa(UUID.randomUUID())
                        .primeiroNome("Ismael")
                        .ultimoNome("Junior")
                        .cpf("000.000.000.00")
                        .rg("0000000")
                        .loja(Loja.builder().build())
                        .usuario(Usuario.builder().build())
                        .apelido("Juninho")
                        .build())
        );
        var result = this.fetchPessoaByLojaUseCase.execute(UUID.randomUUID());
        assertThat(result.getFirst().getPrimeiroNome()).isEqualTo("Ismael");
        assertThat(result).isNotNull();
    }
}
