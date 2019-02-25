/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author vinicius.lemos
 */
public class UserService {
    public static UserSS authenticated(){
        // pega o usuario logado no sistema
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
