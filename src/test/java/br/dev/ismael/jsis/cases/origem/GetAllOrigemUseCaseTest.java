package br.dev.ismael.jsis.cases.origem;

import br.dev.ismael.jsis.domain.application.cases.origem.GetAllOrigemUseCase;
import br.dev.ismael.jsis.domain.application.dto.origem.OrigemResponseDTO;
import br.dev.ismael.jsis.domain.application.repositories.OrigemRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Origem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllOrigemUseCaseTest {
    @Mock
    private OrigemRepository origemRepository;

    @InjectMocks
    private GetAllOrigemUseCase sut;

    @Test
    @DisplayName("Deve retornar todas as origem cadastradas.")
    public void test_successo(){
        List<Origem> origens = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Origem origem = Origem.builder()
                    .idOrigem(i + 1)
                    .nome("Origem " + i)
                    .descricao("Descricao")
                    .build();
            origens.add(origem);
        }

        when(this.origemRepository.findAll()).thenReturn(origens);

        List<OrigemResponseDTO> result = this.sut.execute();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(10);
    }
}
