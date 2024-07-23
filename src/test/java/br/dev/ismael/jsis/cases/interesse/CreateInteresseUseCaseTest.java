package br.dev.ismael.jsis.cases.interesse;

import br.dev.ismael.jsis.domain.application.cases.interesse.CreateInteresseUseCase;
import br.dev.ismael.jsis.domain.application.dto.interesse.InteresseRequestDTO;
import br.dev.ismael.jsis.domain.application.repositories.InteresseRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Interesse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateInteresseUseCaseTest {
    @Mock
    private InteresseRepository interesseRepository;

    @InjectMocks
    private CreateInteresseUseCase createInteresseUseCase;

    @Test
    @DisplayName("Deve cadastrar um interesse.")
    public void test_sucesso(){
        when(this.interesseRepository.save(any())).thenReturn(
                Interesse.builder()
                        .idInteresse(1)
                        .titulo("Casa")
                        .descricao("Quer uma casa de até 600mil reais á vista.")
                        .build()
        );
        var result = this.createInteresseUseCase.execute(
                InteresseRequestDTO.builder()
                        .titulo("Casa")
                        .descricao("Quer uma casa de até 600mil reais á vista.")
                        .build()
        );
        assertThat(result.getTitulo()).isEqualTo("Casa");
    }
}
