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
public class FileException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    
    public FileException(String msg){
        super(msg);
    }
    public FileException(String msg, Throwable cause){
        super(msg, cause);
    }

    
}
