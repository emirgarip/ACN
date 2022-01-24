package com.example.acn.demo.auth;

import com.example.acn.demo.entity.Users;
import com.example.acn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class for providing customized authentication.
 * @author emir
 */
@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;


    /**
     * This method for customizing. First of all, I need to add to context my UserRepository,
     * because I have to call a method for checking credentials and get authority for user, which tries to reach to service.
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        userRepository  = SpringContext.getBean(UserRepository.class);
        String name = authentication.getName();

        String password = authentication.getCredentials().toString();

        Users user = userRepository.findByUsernameAndPassword(name, password);
        if (Objects.nonNull(user)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            user.getAuthoritiesSet().forEach(authority -> {
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
                authorities.add(simpleGrantedAuthority);
            });
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return authenticationToken;
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
