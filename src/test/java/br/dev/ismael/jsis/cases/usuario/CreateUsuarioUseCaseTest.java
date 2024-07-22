package br.dev.ismael.jsis.cases.usuario;

import br.dev.ismael.jsis.domain.application.cases.usuario.CreateUsuarioUseCase;
import br.dev.ismael.jsis.domain.application.dto.auth.UsuarioRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUsuarioUseCaseTest {
    @Mock
    private LojaRepository lojaRepository;
    @Mock
    private DepartamentoRepository departamentoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateUsuarioUseCase createUsuarioUseCase;

    @Test
    @DisplayName("Deve ser possível criar um usuário")
    public void sucesso_test(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        Departamento departamento = Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build();
        when(this.lojaRepository.findById(loja.getIdLoja())).thenReturn(Optional.of(loja));
        when(this.departamentoRepository.findById(departamento.getIdDepartamento())).thenReturn(Optional.of(departamento));
        when(this.usuarioRepository.findByEmailAndLojaIdLoja(any(String.class),any(UUID.class))).thenReturn(Optional.empty());
        when(this.usuarioRepository.save(any(Usuario.class))).thenReturn(Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .email("teste@gmail.com")
                .senha("senha")
                .loja(loja)
                .departamento(departamento)
                .build());
        var result = this.createUsuarioUseCase.execute(
                UsuarioRequestDTO.builder()
                        .email("teste@gmail.com")
                        .senha("senha")
                        .fkLoja(loja.getIdLoja())
                        .fkDepartamento(departamento.getIdDepartamento())
                        .build()
        );
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Usuario.class);
    }

    @Test
    @DisplayName("Não deve ser possível criar um usuário, caso nao encontre a loja.")
    public void test_loja_inexistente(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        Departamento departamento = Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build();
        when(this.lojaRepository.findById(loja.getIdLoja())).thenReturn(Optional.of(loja));
        when(this.usuarioRepository.findByEmailAndLojaIdLoja(any(String.class),any(UUID.class))).thenReturn(Optional.empty());

        try {
            this.createUsuarioUseCase.execute(
                    UsuarioRequestDTO.builder()
                            .email("teste@gmail.com")
                            .senha("senha")
                            .fkLoja(loja.getIdLoja())
                            .fkDepartamento(departamento.getIdDepartamento())
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }

    }

    @Test
    @DisplayName("Não deve ser possível criar um usuário, caso nao encontre o departamento.")
    public void test_departamento_inexistente(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        Departamento departamento = Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build();
        when(this.lojaRepository.findById(loja.getIdLoja())).thenReturn(Optional.of(loja));
        when(this.departamentoRepository.findById(departamento.getIdDepartamento())).thenReturn(Optional.of(departamento));
        try {
            this.createUsuarioUseCase.execute(
                    UsuarioRequestDTO.builder()
                            .email("teste@gmail.com")
                            .senha("senha")
                            .fkLoja(loja.getIdLoja())
                            .fkDepartamento(departamento.getIdDepartamento())
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }

    @Test
    @DisplayName("Não deve ser possível criar um usuário, caso nao o usuário já esta cadastrado na loja.")
    public void test_usuario_ja_cadastrado(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        Departamento departamento = Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build();
        when(this.usuarioRepository.findByEmailAndLojaIdLoja(any(String.class),any(UUID.class))).thenReturn(Optional.of(Usuario.builder().build()));
        try {
            this.createUsuarioUseCase.execute(
                    UsuarioRequestDTO.builder()
                            .email("teste@gmail.com")
                            .senha("senha")
                            .fkLoja(loja.getIdLoja())
                            .fkDepartamento(departamento.getIdDepartamento())
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(JaCadastradoErro.class);
        }
    }

}
