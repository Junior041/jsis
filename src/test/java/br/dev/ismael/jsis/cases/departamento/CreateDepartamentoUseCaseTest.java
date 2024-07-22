package br.dev.ismael.jsis.cases.departamento;

import br.dev.ismael.jsis.domain.application.cases.departamento.CreateDepartamentoUseCase;
import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
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
public class CreateDepartamentoUseCaseTest {

    @Mock
    private DepartamentoRepository departamentoRepository;
    @Mock
    private LojaRepository lojaRepository;
    @InjectMocks
    private CreateDepartamentoUseCase createDepartamentoUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar um departamento.")
    public void test_sucesso(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        when(this.lojaRepository.findById(any(UUID.class))).thenReturn(Optional.of(loja));
        when(this.departamentoRepository.save(any(Departamento.class))).thenReturn(Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build());
        var result = this.createDepartamentoUseCase.execute(
                DepartamentoRequestDTO.builder()
                        .nome("Adiministração")
                        .fkLoja(loja.getIdLoja())
                        .icone("icone.png")
                        .titulo("Adiministração")
                        .build()
        );

        assertThat(result).isNotNull();
        assertThat(result.getIdDepartamento()).isEqualTo(1);
    }
    @Test
    @DisplayName("Não deve ser possível cadastrar um departamento caso não ache a loja.")
    public void test_loja_nao_encontrada(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsável")
                .razaoSocial("teste SA")
                .createdAt(LocalDateTime.now())
                .build();
        when(this.lojaRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        try {
            this.createDepartamentoUseCase.execute(
                    DepartamentoRequestDTO.builder()
                            .nome("Adiministração")
                            .fkLoja(loja.getIdLoja())
                            .icone("icone.png")
                            .titulo("Adiministração")
                            .build()
            );
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }

    }

}
