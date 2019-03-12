/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.resources.expections;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinicius.lemos
 */
public class ValidationError extends StandardError{
     private static final long serialVersionUID = 1L;
    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }
    
   
    /**
     * @return the list
     */
    public List<FieldMessage> getErrors() {
        return erros;
    }

    /**
     * @param fieldName
     * @param message
     * @param erros the list to set
     */
    public void addError(String fieldName, String message){
        erros.add(new FieldMessage(fieldName, message));
    }
    
    
}
