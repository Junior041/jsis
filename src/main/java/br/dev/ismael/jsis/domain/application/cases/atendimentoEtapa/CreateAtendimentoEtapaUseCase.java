package br.dev.ismael.jsis.domain.application.cases.atendimentoEtapa;

import br.dev.ismael.jsis.domain.application.dto.atendimentoEtapa.AtendimentoEtapaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.AtendimentoEtapaRepository;
import br.dev.ismael.jsis.domain.application.repositories.AtendimentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.EtapaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Atendimento;
import br.dev.ismael.jsis.domain.enterprise.entities.AtendimentoEtapa;
import br.dev.ismael.jsis.domain.enterprise.entities.Etapa;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAtendimentoEtapaUseCase {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private EtapaRepository etapaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AtendimentoEtapaRepository atendimentoEtapaRepository;

    public AtendimentoEtapa execute(AtendimentoEtapaRequestDTO atendimentoEtapaRequestDTO) {
        Atendimento atendimento = this.atendimentoRepository.findById(atendimentoEtapaRequestDTO.getFkAtendimento()).orElseThrow(DadoNaoEncontradoErro::new);
        Etapa etapa = this.etapaRepository.findById(atendimentoEtapaRequestDTO.getFkEtapa()).orElseThrow(DadoNaoEncontradoErro::new);
        Usuario usuario = this.usuarioRepository.findById(atendimentoEtapaRequestDTO.getFkUsuario()).orElseThrow(DadoNaoEncontradoErro::new);

        AtendimentoEtapa atendimentoEtapa = AtendimentoEtapa.builder()
                .atendimento(atendimento)
                .etapa(etapa)
                .usuario(usuario)
                .descricao(atendimentoEtapaRequestDTO.getDescricao())
                .build();

        return this.atendimentoEtapaRepository.save(atendimentoEtapa);
    }

}
