package br.dev.ismael.jsis.cases.emailPessoa;

import br.dev.ismael.jsis.domain.application.cases.emailPessoa.FetchEmailPessoaByPessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.emailPessoa.EmailPessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EmailPessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailPessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchEmailPessoaUseCaseTest {
    @Mock
    private EmailPessoaRepository emailPessoaRepository;

    @InjectMocks
    private FetchEmailPessoaByPessoaUseCase fetchEmailPessoaByPessoaUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de email de uma pessoa.")
    public void test_sucesso(){
        List<EmailPessoa> emailPessoaRepositoryList = new ArrayList<>();
        Pessoa pessoa = Pessoa.builder()
                .idPessoa(UUID.randomUUID())
                .primeiroNome("Ismael")
                .ultimoNome("Junior")
                .cpf("000.000.000-00")
                .rg("0000000")
                .loja(Loja.builder().build())
                .usuario(Usuario.builder().build())
                .apelido("Juninho")
                .build();
        for (int i = 0; i < 4; i++) {
            EmailPessoa emailPessoa = EmailPessoa.builder()
                    .idEmailPessoa(UUID.randomUUID())
                    .email("teste@gmail.com")
                    .recebeNovidades(true)
                    .pessoa(pessoa)
                    .build();
            emailPessoaRepositoryList.add(emailPessoa);
        }
        when(this.emailPessoaRepository.findEmailPessoaByPessoaIdPessoa(any())).thenReturn(emailPessoaRepositoryList);
        List<EmailPessoaResponseDTO> result = this.fetchEmailPessoaByPessoaUseCase.execute(pessoa.getIdPessoa());
        assertThat(result.size()).isEqualTo(4);

    }
}
