package br.dev.ismael.jsis.cases.loja;

import br.dev.ismael.jsis.domain.application.cases.loja.GetAllLojaUseCase;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GerAllLojaUseCaseTest {
    @Mock
    private LojaRepository lojaRepository;

    @InjectMocks
    private GetAllLojaUseCase sut;

    @Test
    @DisplayName("Deve retornar uma lista de lojas ")
    public void test_sucesso(){
        List<Loja> lojas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Loja loja = Loja.builder()
                    .idLoja(UUID.randomUUID())
                    .cpfCnpj("000.000.000-00")
                    .nomeResponsavel("Teste Responsavel")
                    .razaoSocial("teste SA")
                    .build();
            lojas.add(loja);
        }

        when(this.lojaRepository.findAll()).thenReturn(lojas);

        List<Loja> result = this.sut.execute();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(10);
    }

}
