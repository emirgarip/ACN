package com.example.acn.demo;

import org.springframework.context.annotation.Configuration;
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
     * This is for open get-features endpoint for everyone.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/get-features").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
