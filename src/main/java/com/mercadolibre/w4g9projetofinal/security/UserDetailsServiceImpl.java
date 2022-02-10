package com.mercadolibre.w4g9projetofinal.security;

import com.mercadolibre.w4g9projetofinal.entity.User;
import com.mercadolibre.w4g9projetofinal.repository.UserRepository;
import com.mercadolibre.w4g9projetofinal.security.entity.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/***
 * Classe de serviço para procurar usuário existente no DB
 *
 * @author Marcos Sá
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("Username not found: " + username));
        return new UserSS(user.getId() ,user.getUsername(), user.getPassword(), user.getProfile());
    }
}
