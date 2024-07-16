package br.dev.ismael.jsis.domain.application.cases.usuario;

import br.dev.ismael.jsis.domain.application.dto.UsuarioRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CreateUsuarioUseCase {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;
    @Autowired
    private LojaRepository lojaRepository;

    public Usuario execute(UsuarioRequestDTO usuarioRequestDTO){
        this.usuarioRepository.findByEmailAndLoja(usuarioRequestDTO.getEmail(), usuarioRequestDTO.getFkLoja()).ifPresent(usuario -> {throw new JaCadastradoErro();});
        Loja loja = this.lojaRepository.findById(usuarioRequestDTO.getFkLoja()).orElseThrow(() -> new DadoNaoEncontradoErro());
        Departamento departamento = this.departamentoRepository.findById(usuarioRequestDTO.getFkDepartamento()).orElseThrow(() -> new DadoNaoEncontradoErro());
        Usuario usuario = Usuario.builder()
                .email(usuarioRequestDTO.getEmail())
                .senha(usuarioRequestDTO.getSenha())
                .departamento(departamento)
                .loja(loja)
                .build();
        return this.usuarioRepository.save(usuario);

    }

}
