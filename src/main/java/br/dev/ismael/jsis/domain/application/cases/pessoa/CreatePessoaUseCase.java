package br.dev.ismael.jsis.domain.application.cases.pessoa;

import br.dev.ismael.jsis.domain.application.dto.pessoa.PessoaRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.PessoaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Pessoa;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePessoaUseCase {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private LojaRepository lojaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public Pessoa execute(PessoaRequestDTO pessoaRequestDTO){
        Loja loja = this.lojaRepository.findById(pessoaRequestDTO.getFkLoja()).orElseThrow(DadoNaoEncontradoErro::new);
        Usuario cadastradoPorUsuarioId = this.usuarioRepository.findById(pessoaRequestDTO.getCadastradoPorUsuarioId()).orElseThrow(DadoNaoEncontradoErro::new);
        this.pessoaRepository.findByCpfAndLojaIdLoja(pessoaRequestDTO.getCpf(), pessoaRequestDTO.getFkLoja()).ifPresent((pessoa)-> {
            throw new JaCadastradoErro();
        });


        Pessoa pessoa = Pessoa.builder()
                .loja(loja)
                .dataNascimento(pessoaRequestDTO.getDataNascimento())
                .usuario(cadastradoPorUsuarioId)
                .ultimoNome(pessoaRequestDTO.getUltimoNome())
                .rg(pessoaRequestDTO.getRg())
                .cpf(pessoaRequestDTO.getCpf())
                .apelido(pessoaRequestDTO.getApelido())
                .build();
        return this.pessoaRepository.save(pessoa);
    }
}
