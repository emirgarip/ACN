package com.example.acn.demo;

import com.example.acn.demo.auth.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This is for override some spring security configurations while starting application.
 * @author emir
 */
@EnableWebSecurity
@Configuration
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * This is for reach to h2-console without spring authorization.
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    /**
     * This is for enable my custimized provider.
     * @param auth
     * @throws Exception
     */
    @Override
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }


    /**
     * This is for open get-features endpoint for everyone.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/feature-management/features").hasAnyAuthority("user", "admin")
                .antMatchers(HttpMethod.POST, "/feature-management/features").hasAuthority("admin")
                .antMatchers(HttpMethod.PUT, "/feature-management/features").hasAuthority("admin")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
