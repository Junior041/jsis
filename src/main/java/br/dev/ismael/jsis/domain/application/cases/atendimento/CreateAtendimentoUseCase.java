package br.dev.ismael.jsis.domain.application.cases.atendimento;

import br.dev.ismael.jsis.domain.application.dto.atendimento.AtendimentoRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.repositories.*;
import br.dev.ismael.jsis.domain.enterprise.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateAtendimentoUseCase {
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private OrigemRepository origemRepository;
    @Autowired
    private InteresseRepository interesseRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Atendimento execute(AtendimentoRequestDTO atendimentoRequestDTO) {
        Pessoa pessoa = this.pessoaRepository.findById(atendimentoRequestDTO.getFkPessoa()).orElseThrow(DadoNaoEncontradoErro::new);
        Origem origem = this.origemRepository.findById(atendimentoRequestDTO.getFkOrigem()).orElseThrow(DadoNaoEncontradoErro::new);
        Interesse interesse = this.interesseRepository.findById(atendimentoRequestDTO.getFkInteresse()).orElseThrow(DadoNaoEncontradoErro::new);
        Usuario usuario = this.usuarioRepository.findById(atendimentoRequestDTO.getCadastradoPorUsuarioId()).orElseThrow(DadoNaoEncontradoErro::new);

        Atendimento atendimento = Atendimento.builder()
                .pessoa(pessoa)
                .origem(origem)
                .interesse(interesse)
                .descricao(origem.getDescricao())
                .usuario(usuario)
                .build();

        return this.atendimentoRepository.save(atendimento);

    }
}
