/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services.expections;

/**
 *
 * @author Vinicius Lemos
 */
public class AuthorizationException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    
    public AuthorizationException(String msg){
        super(msg);
    }
    public AuthorizationException(String msg, Throwable cause){
        super(msg, cause);
    }

    
}
