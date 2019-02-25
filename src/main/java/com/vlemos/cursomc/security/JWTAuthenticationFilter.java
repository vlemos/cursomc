/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlemos.cursomc.dto.CredenciaisDTO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author vinicius.lemos
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        
    }
    
 
    // tentativa de autenticação
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException{
        CredenciaisDTO creds;
        try {
            creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getSenha(), new ArrayList<>());
            Authentication auth = authenticationManager.authenticate(authToken);
            return auth;
            
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
        
    }
    @Override
     protected void  successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain filter, Authentication auth) throws IOException, ServletException{
         
         String username = ((UserSS) auth.getPrincipal()).getUsername();
         String token = jwtUtil.generateToken(username);
         res.addHeader("Authorization", "Bearer" + token);
        
    }
    
    
}
