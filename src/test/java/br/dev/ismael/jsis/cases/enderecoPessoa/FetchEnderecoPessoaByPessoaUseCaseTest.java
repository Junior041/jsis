package br.dev.ismael.jsis.cases.enderecoPessoa;

import br.dev.ismael.jsis.domain.application.cases.enderecoPessoa.FetchEnderecoPessoaByPessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.enderecoPessoa.EnderecoPessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EnderecoPessoaRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchEnderecoPessoaByPessoaUseCaseTest {

    @Mock
    private EnderecoPessoaRepository enderecoPessoaRepository;

    @InjectMocks
    private FetchEnderecoPessoaByPessoaUseCase fetchEnderecoPessoaByPessoaUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de endereços")
    public void testSucesso() {
        // Criação de uma pessoa para teste
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

        List<EnderecoPessoa> enderecoPessoaRepositoryList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            EnderecoPessoa enderecoPessoa = EnderecoPessoa.builder()
                    .idEnderecoPessoa(UUID.randomUUID())
                    .logradouro("Avenida")
                    .estado("Estado")
                    .cep("8888888-88")
                    .cidade("Brusque")
                    .estado("Santa Catarina")
                    .rua("Otto Renaux")
                    .pessoa(pessoa)
                    .build();
            enderecoPessoaRepositoryList.add(enderecoPessoa);
        }
        when(enderecoPessoaRepository.findEnderecoPessoaByPessoaIdPessoa(any()))
                .thenReturn(enderecoPessoaRepositoryList);
        List<EnderecoPessoaResponseDTO> result = fetchEnderecoPessoaByPessoaUseCase.execute(pessoa.getIdPessoa());
        assertThat(result.size()).isEqualTo(4);
    }
}
