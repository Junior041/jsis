package br.dev.ismael.jsis.cases.etapa;

import br.dev.ismael.jsis.domain.application.cases.etapa.FetchEtapaByDepartamentoUseCase;
import br.dev.ismael.jsis.domain.application.dto.departamento.DepartamentoResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FetchEtapaByDepartamentoUseCaseTest {
    @Mock
    private EtapaRepository etapaRepository;

    @InjectMocks
    private FetchEtapaByDepartamentoUseCase fetchEtapaByDepartamentoUseCase;

    @DisplayName("Deve retornar uma lista de EtapaResponseDTO")
    @Test
    public void test_sucesso(){
        when(this.etapaRepository.findByFkDepartamento(any())).thenReturn(List.of(Etapa.builder()
                        .idEtapa(1)
                        .prioridade(1)
                        .corHexadecimal("#FFFF")
                        .nome("Em espera")
                        .descricao("Descrição")
                        .departamento(
                                Departamento.builder()
                                .idDepartamento(1)
                                .loja(Loja.builder()
                                        .idLoja(UUID.randomUUID())
                                        .cpfCnpj("000.000.000-00")
                                        .nomeResponsavel("Teste Responsável")
                                        .razaoSocial("teste SA")
                                        .createdAt(LocalDateTime.now())
                                        .build())
                                .nome("nome")
                                .titulo("Vendas")
                                .build())
                .build()));
        var result = this.fetchEtapaByDepartamentoUseCase.execute(1);
        assertThat(result).isNotNull();
    }
}
