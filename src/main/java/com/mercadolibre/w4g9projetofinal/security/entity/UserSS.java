package com.mercadolibre.w4g9projetofinal.security.entity;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/***
 * Classe UserSS
 *
 * @author Marcos Sá
 */
@Getter
public class UserSS implements UserDetails {
    private static final long serialVersionUID = 7179078869364575101L;

    private final Long id;
    private final String username;
    private final String password;
    private final Collection <?extends GrantedAuthority> authorities;

    public UserSS(Long id, String username, String password, Set<Profile> perfis) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = perfis.stream()
                .map(x -> new SimpleGrantedAuthority(x.getDescricao()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Profile profile) {
        return getAuthorities().contains(new SimpleGrantedAuthority(profile.getDescricao()));
    }
}
