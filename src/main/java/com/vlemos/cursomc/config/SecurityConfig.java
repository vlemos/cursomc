/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.config;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author vinicius.lemos
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private Environment env;
    
    private static final String[] PUBLIC_MATCHES = {
      "/h2-console/**",
    };
    
     private static final String[] PUBLIC_MATCHES_GET = {
        "/produtos/**",
        "/categorias/**"
    };
    
    @Override
    public void configure(HttpSecurity http)throws Exception {
        if(Arrays.asList(env.getActiveProfiles()).contains("test")){ //pega o profile ativo de TESTE
            http.headers().frameOptions().disable(); // libera o acesso ao banco H2
        }
        
        http.cors().and().csrf().disable(); // desabilita proteção contra CSRF para aplicações STATELESS, para aplicações sem seção do usuario
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHES_GET).permitAll() //somente permite GET para esta lista
                .antMatchers(PUBLIC_MATCHES).permitAll() // para esta lista esta tudo liberado
                .anyRequest().authenticated();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //garante que não criaremos seção de usuario
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
       final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
       return source;
    }
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    } 
}
