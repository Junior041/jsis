package br.dev.ismael.jsis.domain.application.cases.emailLoja;

import br.dev.ismael.jsis.domain.application.dto.emailLoja.EmailLojaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.EmailLojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.EmailLoja;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateEmailLojaUseCase {

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private EmailLojaRepository emailLojaRepository;

    public EmailLoja execute(EmailLojaRequestDTO emailLojaRequestDTO){
        Loja loja = this.lojaRepository.findById(emailLojaRequestDTO.getFkLoja()).orElseThrow(DadoNaoEncontradoErro::new);
        EmailLoja emailLoja = EmailLoja.builder()
                .titulo(emailLojaRequestDTO.getTitulo())
                .recebeLeads(emailLojaRequestDTO.getRecebeLeads())
                .email(emailLojaRequestDTO.getEmail())
                .loja(loja)
                .createdAt(LocalDateTime.now())
                .build();
        return this.emailLojaRepository.save(emailLoja);
    }
}
