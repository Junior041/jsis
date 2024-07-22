package br.dev.ismael.jsis.cases.pessoa;

import br.dev.ismael.jsis.domain.application.cases.pessoa.CreatePessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
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
public class CreatePessoaUseCaseTest {
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private LojaRepository lojaRepository;

    @InjectMocks
    private CreatePessoaUseCase createPessoaUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar uma pessoa.")
    public void test_sucesso() {
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        when(this.lojaRepository.findById(any())).thenReturn(Optional.of(loja));
        Usuario usuario = Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .email("teste@gmail.com")
                .senha("senha")
                .loja(loja)
                .departamento(Departamento.builder().idDepartamento(1).build())
                .build();
        when(this.usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(this.pessoaRepository.save(any())).thenReturn(
                Pessoa.builder()
                        .idPessoa(UUID.randomUUID())
                        .primeiroNome("Ismael")
                        .ultimoNome("Junior")
                        .cpf("000.000.000.00")
                        .rg("0000000")
                        .loja(loja)
                        .usuario(usuario)
                        .apelido("Juninho")
                        .build()
        );

        var result = this.createPessoaUseCase.execute(PessoaRequestDTO.builder()
                .apelido("Juninho")
                .dataNascimento("15082002")
                .cadastradoPorUsuarioId(usuario.getIdUsuario())
                .cpf("000.000.000-00")
                .fkLoja(loja.getIdLoja())
                .primeiroNome("Ismael")
                .ultimoNome("Junior")
                .rg("999999999")
                .build());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Pessoa.class);
        assertThat(result.getPrimeiroNome()).isEqualTo("Ismael");
        assertThat(result.getLoja().getIdLoja()).isEqualTo(loja.getIdLoja());
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar uma pessoa caso nao encontre a loja.")
    public void test_erro_loja_nao_encontrada() {
        when(this.lojaRepository.findById(any())).thenReturn(Optional.empty());
        try {
            this.createPessoaUseCase.execute(PessoaRequestDTO.builder()
                    .apelido("Juninho")
                    .dataNascimento("15082002")
                    .cadastradoPorUsuarioId(UUID.randomUUID())
                    .cpf("000.000.000-00")
                    .fkLoja(UUID.randomUUID())
                    .primeiroNome("Ismael")
                    .ultimoNome("Junior")
                    .rg("999999999")
                    .build());
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar uma pessoa caso nao encontre a usuario que a cadastrou.")
    public void test_erro_usuario_nao_encontrado() {
        when(this.lojaRepository.findById(any())).thenReturn(Optional.of(Loja.builder().build()));
        when(this.usuarioRepository.findById(any())).thenReturn(Optional.empty());
        try {
            this.createPessoaUseCase.execute(PessoaRequestDTO.builder()
                    .apelido("Juninho")
                    .dataNascimento("15082002")
                    .cadastradoPorUsuarioId(UUID.randomUUID())
                    .cpf("000.000.000-00")
                    .fkLoja(UUID.randomUUID())
                    .primeiroNome("Ismael")
                    .ultimoNome("Junior")
                    .rg("999999999")
                    .build());
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar uma pessoa caso o cpd esteja repetido na loja.")
    public void test_erro_cpf_duplicado_na_loja() {
        UUID idLoja = UUID.randomUUID();
        Loja loja = Loja.builder()
                .idLoja(idLoja)
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        when(this.lojaRepository.findById(any())).thenReturn(Optional.of(loja));
        Usuario usuario = Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .email("teste@gmail.com")
                .senha("senha")
                .loja(loja)
                .departamento(Departamento.builder().idDepartamento(1).build())
                .build();
        when(this.usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(this.pessoaRepository.findByCpfAndLojaIdLoja(any(),any())).thenReturn(Optional.of(
                Pessoa.builder()
                        .loja(loja)
                        .idPessoa(UUID.randomUUID())
                        .build()
        ));
        try {
            this.createPessoaUseCase.execute(PessoaRequestDTO.builder()
                    .apelido("Juninho")
                    .dataNascimento("15082002")
                    .cadastradoPorUsuarioId(UUID.randomUUID())
                    .cpf("000.000.000-00")
                    .fkLoja(UUID.randomUUID())
                    .primeiroNome("Ismael")
                    .ultimoNome("Junior")
                    .rg("999999999")
                    .build());
        }catch (Exception e){
            assertThat(e).isInstanceOf(JaCadastradoErro.class);
        }
    }

}
