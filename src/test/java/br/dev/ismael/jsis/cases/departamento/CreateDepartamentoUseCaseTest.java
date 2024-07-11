package br.dev.ismael.jsis.cases.departamento;

import br.dev.ismael.jsis.domain.application.cases.departamento.CreateDepartamentoUseCase;
import br.dev.ismael.jsis.domain.application.dto.DepartamentoRequestDTO;
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
    private LojaRepository lojaRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private CreateDepartamentoUseCase sut;


    @Test
    @DisplayName("Deve ser possível cadastrar um departamento.")
    public void test_sucesso(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .razaoSocial("Loja Mock")
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Responsavel Mock")
                .createdAt(LocalDateTime.now())
                .build();
        when(this.lojaRepository.findById(any(UUID.class))).thenReturn(
                Optional.of(loja)
        );

        when(this.departamentoRepository.save(any())).thenReturn(
                Departamento.builder().idDepartamento(1).icone("teste.png").nome("departamento").titulo("departamento").loja(loja).build()
        );

        var result = this.sut.execute(
                DepartamentoRequestDTO.builder()
                        .nome("teste")
                        .titulo("teste")
                        .icone("teste")
                        .fk_loja(UUID.randomUUID())
                        .build()
        );
        assertThat(result).hasFieldOrProperty("idDepartamento");
        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Departamento.class);

    }
}
