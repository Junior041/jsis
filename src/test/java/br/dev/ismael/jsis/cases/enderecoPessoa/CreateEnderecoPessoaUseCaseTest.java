package br.dev.ismael.jsis.cases.enderecoPessoa;

import br.dev.ismael.jsis.domain.application.cases.enderecoPessoa.CreateEnderecoPessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.enderecoPessoa.EnderecoPessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EnderecoPessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EnderecoPessoa;
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
public class CreateEnderecoPessoaUseCaseTest {
    @Mock
    private EnderecoPessoaRepository enderecoPessoaRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private CreateEnderecoPessoaUseCase createEnderecoPessoaUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar o endereco de uma pessoa")
    public void test_sucesso() {
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
        when(this.enderecoPessoaRepository.save(any())).thenReturn(
                EnderecoPessoa.builder()
                        .idEnderecoPessoa(UUID.randomUUID())
                        .cep("00000-00")
                        .rua("Otto Renaux")
                        .cidade("Brusque")
                        .estado("Santa Catarina")
                        .logradouro("Avenida")
                        .pessoa(pessoa)
                        .build()
        );

        var result = this.createEnderecoPessoaUseCase.execute(EnderecoPessoaRequestDTO.builder()
                .fkPessoa(pessoa.getIdPessoa())
                .cep("00000-00")
                .rua("Otto Renaux")
                .cidade("Brusque")
                .estado("Santa Catarina")
                .logradouro("Avenida")
                .build());

        assertThat(result.getPessoa().getIdPessoa()).isEqualTo(pessoa.getIdPessoa());
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar um endereço se não encontrar a pessoa.")
    public void test_erro_pessoa_nao_encontrada() {

        try {
            this.createEnderecoPessoaUseCase.execute(EnderecoPessoaRequestDTO.builder()
                    .fkPessoa(UUID.randomUUID())
                    .cep("00000-00")
                    .rua("Otto Renaux")
                    .cidade("Brusque")
                    .estado("Santa Catarina")
                    .logradouro("Avenida")
                    .build());
        } catch (Exception e) {
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }
}
