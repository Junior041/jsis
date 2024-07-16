package br.dev.ismael.jsis.domain.application.cases.usuario;

import br.dev.ismael.jsis.domain.application.dto.UsuarioRequestDTO;
import br.dev.ismael.jsis.domain.application.errors.DadoNaoEncontradoErro;
import br.dev.ismael.jsis.domain.application.errors.JaCadastradoErro;
import br.dev.ismael.jsis.domain.application.repositories.DepartamentoRepository;
import br.dev.ismael.jsis.domain.application.repositories.LojaRepository;
import br.dev.ismael.jsis.domain.application.repositories.UsuarioRepository;
import br.dev.ismael.jsis.domain.enterprise.entities.Departamento;
import br.dev.ismael.jsis.domain.enterprise.entities.Loja;
import br.dev.ismael.jsis.domain.enterprise.entities.UserRoles;
import br.dev.ismael.jsis.domain.enterprise.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario execute(UsuarioRequestDTO usuarioRequestDTO){
        this.usuarioRepository.findByEmailAndLojaIdLoja(usuarioRequestDTO.getEmail(), usuarioRequestDTO.getFkLoja()).ifPresent(usuario -> {throw new JaCadastradoErro();});
        Loja loja = this.lojaRepository.findById(usuarioRequestDTO.getFkLoja()).orElseThrow(() -> new DadoNaoEncontradoErro());
        Departamento departamento = this.departamentoRepository.findById(usuarioRequestDTO.getFkDepartamento()).orElseThrow(() -> new DadoNaoEncontradoErro());
        String senha = this.passwordEncoder.encode(usuarioRequestDTO.getSenha());
        Usuario usuario = Usuario.builder()
                .email(usuarioRequestDTO.getEmail())
                .senha(senha)
                .departamento(departamento)
                .loja(loja)
                .role(usuarioRequestDTO.getRole())
                .build();
        return this.usuarioRepository.save(usuario);

    }

}
