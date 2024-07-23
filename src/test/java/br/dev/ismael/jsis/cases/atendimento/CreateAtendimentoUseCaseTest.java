package br.dev.ismael.jsis.cases.atendimento;

import br.dev.ismael.jsis.domain.application.cases.atendimento.CreateAtendimentoUseCase;
import br.dev.ismael.jsis.domain.application.dto.atendimento.AtendimentoRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.*;
import br.dev.ismael.jsis.domain.enterprise.entities.*;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateAtendimentoUseCaseTest {
    @Mock
    private AtendimentoRepository atendimentoRepository;
    @Mock
    private OrigemRepository origemRepository;
    @Mock
    private InteresseRepository interesseRepository;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private CreateAtendimentoUseCase createAtendimentoUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar um atendimento.")
    public void test_sucesso() {
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        Origem origem = Origem.builder()
                .nome("Whatsapp")
                .descricao("Clientes que vieram pelo whatsapp")
                .build();
        Interesse interesse = Interesse.builder()
                .titulo("Casa")
                .descricao("Casa de até 600mil")
                .build();
        Usuario usuario = Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .build();
        Pessoa pessoaAtendimento = Pessoa.builder()
                .idPessoa(UUID.randomUUID())
                .build();
        Atendimento atendimento = Atendimento.builder()
                .usuario(usuario)
                .descricao("Cliente interessado em uma casa")
                .origem(origem)
                .interesse(interesse)
                .loja(loja)
                .idAtendimento(1)
                .build();

        when(this.pessoaRepository.findById(any())).thenReturn(Optional.of(pessoaAtendimento));

        when(this.origemRepository.findById(any())).thenReturn(Optional.of(origem));

        when(this.interesseRepository.findById(any())).thenReturn(Optional.of(interesse));

        when(this.usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));

        when(this.atendimentoRepository.save(any())).thenReturn(atendimento);

        var result = this.createAtendimentoUseCase.execute(AtendimentoRequestDTO.builder()
                .descricao("Cliente interessado em uma casa")
                .fkInteresse(interesse.getIdInteresse())
                .fkOrigem(origem.getIdOrigem())
                .fkPessoa(pessoaAtendimento.getIdPessoa())
                .cadastradoPorUsuarioId(usuario.getIdUsuario())
                .build());
        assertThat(result.getIdAtendimento()).isEqualTo(1);
    }
    @Test
    @DisplayName("Não deve ser possível cadastrar caso não ache a pessoa.")
    public void test_erro_pessoa_nao_encontrada() {
        // Mock repository response
        when(pessoaRepository.findById(any())).thenReturn(Optional.empty());

        // Assertions
        assertThatThrownBy(() -> createAtendimentoUseCase.execute(AtendimentoRequestDTO.builder()
                .descricao("Cliente interessado em uma casa")
                .fkInteresse(1)
                .fkOrigem(1)
                .fkPessoa(UUID.randomUUID())
                .cadastradoPorUsuarioId(UUID.randomUUID())
                .build())).isInstanceOf(DadoNaoEncontradoErro.class);
    }
}
