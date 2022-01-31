package com.mercadolibre.w4g9projetofinal.security;

import com.mercadolibre.w4g9projetofinal.entity.enums.Profile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
public class UserSS implements UserDetails {
    private static final long serialVersionUID = 7179078869364575101L;

    private Long id;
    private String email;
    private String pass;
    private Collection <? extends GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public UserSS(Long id, String email, String senha, Set<Profile> perfis) {
        super();
        this.id = id;
        this.email = email;
        this.pass = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pass;
    }

    @Override
    public String getUsername() {
        return email;
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
}
