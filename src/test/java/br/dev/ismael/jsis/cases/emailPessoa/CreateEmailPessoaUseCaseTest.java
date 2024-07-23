package br.dev.ismael.jsis.cases.emailPessoa;

import br.dev.ismael.jsis.domain.application.cases.emailPessoa.CreateEmailPessoaUseCae;
import br.dev.ismael.jsis.domain.application.dto.emailPessoa.EmailPessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EmailPessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEmailPessoaUseCaseTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EmailPessoaRepository emailPessoaRepository;

    @InjectMocks
    private CreateEmailPessoaUseCae createEmailPessoaUseCase;

    @Test
    @DisplayName("Deve ser possível criar o email de uma pessoa.")
    public void test_sucesso(){
        Pessoa pessoa = Pessoa.builder()
                .idPessoa(UUID.randomUUID())
                .loja(Loja.builder().idLoja(UUID.randomUUID()).build())
                .cpf("000.000.000-00")
                .rg("000000000")
                .apelido("Juninho")
                .primeiroNome("Ismael")
                .ultimoNome("Junior")
                .usuario(Usuario.builder().idUsuario(UUID.randomUUID()).build())
                .build();

        when(this.pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        when(this.emailPessoaRepository.save(any())).thenReturn(
                EmailPessoa.builder()
                        .email("teste@gmail.com")
                        .recebeNovidades(true)
                        .pessoa(pessoa)
                        .build()
        );
        var result = this.createEmailPessoaUseCase.execute(
                EmailPessoaRequestDTO.builder()
                        .email("teste@gmail.com")
                        .recebeNovidades(true)
                        .fkPessoa(pessoa.getIdPessoa())
                        .build()
        );
        assertThat(result.getEmail()).isEqualTo("teste@gmail.com");
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar um email caso nao ache a pessoa")
    public void test_erro_pessoa_nao_encontrada(){
        try {
            this.createEmailPessoaUseCase.execute(
                    EmailPessoaRequestDTO.builder()
                            .email("teste@gmail.com")
                            .recebeNovidades(true)
                            .fkPessoa(UUID.randomUUID())
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }
}
