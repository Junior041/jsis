package br.dev.ismael.jsis.domain.enterprise.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idUsuario")
    private UUID idUsuario ;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserRoles role;

    @ManyToOne
    @JoinColumn(name = "fkDepartamento", referencedColumnName = "idDepartamento", nullable = true)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "fkLoja", referencedColumnName = "idLoja", nullable = false)
    private Loja loja;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        switch (this.role){
            case ADMIN -> {
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.ADMIN));
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.GUEST));
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER));
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
            }
            case GUEST -> {
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.GUEST));
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
            }
            case MANAGER -> {
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.MANAGER));
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
            }
            case USER -> {
                list.add(new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
            }
            default -> throw new IllegalArgumentException("Invalid role: " + this.role);
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

}
