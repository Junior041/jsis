package br.dev.ismael.jsis.cases.atendimentoEtapa;

import br.dev.ismael.jsis.domain.application.cases.atendimentoEtapa.CreateAtendimentoEtapaUseCase;
import br.dev.ismael.jsis.domain.application.dto.atendimentoEtapa.AtendimentoEtapaRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.AtendimentoEtapaRepository;
import br.dev.ismael.jsis.domain.application.repositories.AtendimentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateAtendimentoEtapaUseCaseTest {

    @Mock
    private AtendimentoRepository atendimentoRepository;
    @Mock
    private EtapaRepository etapaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private AtendimentoEtapaRepository atendimentoEtapaRepository;
    @InjectMocks
    private CreateAtendimentoEtapaUseCase createAtendimentoEtapaUseCase;

    @Test
    @DisplayName("Deve ser possível cadastrar um atendimento etapa")
    public void test_sucesso() {
        Atendimento atendimento = Atendimento.builder()
                .usuario(Usuario.builder().build())
                .descricao("Cliente interessado em uma casa")
                .origem(Origem.builder().build())
                .interesse(Interesse.builder().build())
                .loja(Loja.builder().build())
                .idAtendimento(1)
                .build();
        Etapa etapa = Etapa.builder()
                .idEtapa(1)
                .prioridade(1)
                .corHexadecimal("#FFFF")
                .nome("Em espera")
                .descricao("Descrição")
                .build();
        Usuario usuario = Usuario.builder()
                .idUsuario(UUID.randomUUID())
                .email("teste@gmail.com")
                .senha("senha")
                .loja(Loja.builder().build())
                .departamento(Departamento.builder().build())
                .build();
        AtendimentoEtapa atendimentoEtapa = AtendimentoEtapa.builder()
                .idAtendimentoEtapa(1)
                .descricao("Pegar verificar se o imóvel está disponível e retornar para o cliente.")
                .atendimento(atendimento)
                .etapa(etapa)
                .usuario(usuario)
                .build();

        when(this.etapaRepository.findById(any())).thenReturn(Optional.of(etapa));
        when(this.atendimentoRepository.findById(any())).thenReturn(Optional.of(atendimento));
        when(this.usuarioRepository.findById(any())).thenReturn(Optional.of(usuario));
        when(this.atendimentoEtapaRepository.save(any())).thenReturn(atendimentoEtapa);

        var result = this.createAtendimentoEtapaUseCase.execute(
                AtendimentoEtapaRequestDTO.builder()
                        .fkAtendimento(atendimento.getIdAtendimento())
                        .fkEtapa(etapa.getIdEtapa())
                        .fkUsuario(usuario.getIdUsuario())
                        .descricao("Pegar verificar se o imóvel está disponível e retornar para o cliente.")
                        .build()
        );

        assertThat(result.getAtendimento()).isEqualTo(atendimento);
        assertThat(result.getIdAtendimentoEtapa()).isEqualTo(1);

    }

}
