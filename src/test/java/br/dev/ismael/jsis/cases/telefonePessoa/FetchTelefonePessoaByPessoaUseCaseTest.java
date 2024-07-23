package br.dev.ismael.jsis.cases.telefonePessoa;

import br.dev.ismael.jsis.domain.application.cases.telefonePessoa.FetchTelefonePessoaByPessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.TelefonePessoaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.TelefonePessoa;
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
public class FetchTelefonePessoaByPessoaUseCaseTest {

    @Mock
    private TelefonePessoaRepository telefonePessoaRepository;

    @InjectMocks
    private FetchTelefonePessoaByPessoaUseCase fetchTelefonePessoaByPessoaUseCase;

    @Test
    @DisplayName("Deve retornar uma lista de telefones")
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

        // Inicialização da lista de TelefonePessoa
        List<TelefonePessoa> telefonePessoaRepositoryList = new ArrayList<>();

        // Adicionando telefones à lista
        for (int i = 0; i < 4; i++) {
            TelefonePessoa telefonePessoa = TelefonePessoa.builder()
                    .idTelefonePessoa(UUID.randomUUID())
                    .ddd("47")
                    .telefone("9923-934" + i)
                    .whatsapp(true)
                    .pessoa(pessoa)
                    .build();
            telefonePessoaRepositoryList.add(telefonePessoa);
        }
        when(telefonePessoaRepository.findTelefonePessoaByPessoaIdPessoa(any()))
                .thenReturn(telefonePessoaRepositoryList);
        List<TelefonePessoaResponseDTO> result = fetchTelefonePessoaByPessoaUseCase.execute(pessoa.getIdPessoa());
        assertThat(result.size()).isEqualTo(4);
    }
}
