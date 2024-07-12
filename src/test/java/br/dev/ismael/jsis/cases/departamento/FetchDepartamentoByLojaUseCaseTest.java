package br.dev.ismael.jsis.cases.departamento;

import br.dev.ismael.jsis.domain.application.cases.departamento.FetchDepartamentoByLojaUseCase;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchDepartamentoByLojaUseCaseTest {
    @Mock
    private LojaRepository lojaRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private FetchDepartamentoByLojaUseCase sut;

    @Test
    @DisplayName("Deve retornar uma lista de departamentos.")
    public void test_sucesso(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .razaoSocial("Loja Mock")
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Responsavel Mock")
                .createdAt(LocalDateTime.now())
                .build();

        List<Departamento> departamentos = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Departamento origem =
                    Departamento.builder()
                            .idDepartamento(i + 1)
                            .loja(loja)
                            .nome("nome" + 1)
                            .titulo("titulo" + 1)
                            .build()
                    ;

            departamentos.add(origem);
        }
        when(this.departamentoRepository.findByLojaId(any(String.class))).thenReturn(departamentos);

        var result = this.sut.execute(loja.getIdLoja().toString());

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(20);

    }
}
