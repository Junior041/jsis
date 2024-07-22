package br.dev.ismael.jsis.cases.etapa;

import br.dev.ismael.jsis.domain.application.cases.etapa.CreateEtapaUseCase;
import br.dev.ismael.jsis.domain.application.dto.DepartamentoRequestDTO;
import br.dev.ismael.jsis.domain.application.dto.EtapaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
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

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateEtapaUseCaseTest {
    @InjectMocks
    private CreateEtapaUseCase createEtapaUseCase;

    @Mock
    private EtapaRepository etapaRepository;

    @Mock
    private DepartamentoRepository departamentoRepository;

    @Test
    @DisplayName("Deve ser possível criar uma etapa.")
    public void test_sucesso(){
        Loja loja = Loja.builder()
                .idLoja(UUID.randomUUID())
                .cpfCnpj("000.000.000-00")
                .nomeResponsavel("Teste Responsavel")
                .razaoSocial("teste SA")
                .build();
        Departamento departamento = Departamento.builder()
                .idDepartamento(1)
                .loja(loja)
                .nome("nome")
                .titulo("titulo")
                .build();
        when(this.departamentoRepository.findById(any(Integer.class))).thenReturn(Optional.of(departamento));
        Etapa etapa = Etapa.builder()
                .idEtapa(1)
                .corHexadecimal("#FFFF")
                .descricao("Etapa em espera.")
                .departamento(departamento)
                .prioridade("1")
                .nome("Em Espera")
                .build();
        when(this.etapaRepository.save(any())).thenReturn(etapa);
        var result = this.createEtapaUseCase.execute(EtapaRequestDTO.builder()
                        .corHexadecimal("#FFFF")
                        .descricao("Etapa em espera.")
                        .fkDepartamento(1)
                        .prioridade("1")
                        .nome("Em Espera")
                .build());

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Etapa.class);
    }

    @Test
    @DisplayName("Não deve ser possível criar uma etapa se não achar um departamento")
    public void test_departamento_nao_encontrado(){
        when(this.departamentoRepository.findById(any(Integer.class))).thenReturn(Optional.empty());
        try {
            this.createEtapaUseCase.execute(EtapaRequestDTO.builder()
                    .corHexadecimal("#FFFF")
                    .descricao("Etapa em espera.")
                    .fkDepartamento(1)
                    .prioridade("1")
                    .nome("Em Espera")
                    .build());
        }catch (Exception e){
            assertThat(e).isInstanceOf(DadoNaoEncontradoErro.class);
        }
    }
}
