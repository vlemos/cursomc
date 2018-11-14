    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.resources.expections;

import com.vlemos.cursomc.services.expections.DataIntegrityException;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Vinicius Lemos
 */

@ControllerAdvice
public class ResourceExcepetionHandler {
    
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e , HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    
     @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e , HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e , HttpServletRequest request){
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
        for(FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(), x.getDefaultMessage());
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    
    }
}
