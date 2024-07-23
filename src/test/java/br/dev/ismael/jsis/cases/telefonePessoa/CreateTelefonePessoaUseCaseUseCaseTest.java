package br.dev.ismael.jsis.cases.telefonePessoa;

import br.dev.ismael.jsis.domain.application.cases.telefonePessoa.CreateTelefonePessoaUseCase;
import br.dev.ismael.jsis.domain.application.dto.telefonePessoa.TelefonePessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTelefonePessoaUseCaseUseCaseTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private TelefonePessoaRepository telefonePessoaRepository;

    @InjectMocks
    private CreateTelefonePessoaUseCase createTelefonePessoaUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar o telefone da pessoa.")
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
        when(this.telefonePessoaRepository.save(any())).thenReturn(
                TelefonePessoa.builder()
                        .idTelefonePessoa(UUID.randomUUID())
                        .ddd("47")
                        .pessoa(pessoa)
                        .telefone("991239346")
                        .whatsapp(true)
                        .build()
        );
        var result = this.createTelefonePessoaUseCase.execute(TelefonePessoaRequestDTO.builder()
                        .ddd("47")
                        .fkPessoa(pessoa.getIdPessoa())
                        .telefone("99123-9346")
                        .whatsapp(true)
                .build());
        assertThat(result.getTelefone()).isEqualTo("991239346");
    }

    @Test
    @DisplayName("Não deve ser possível cadastrar um telefone caso nao ache a pessoa")
    public void test_erro_pessoa_nao_encontrada(){
        try {
            this.createTelefonePessoaUseCase.execute(TelefonePessoaRequestDTO.builder()
                    .ddd("47")
                    .fkPessoa(UUID.randomUUID())
                    .telefone("99123-9346")
                    .whatsapp(true)
                    .build());
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }

}
