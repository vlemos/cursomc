/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services.validation;

import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.dto.ClienteDTO;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.resources.expections.FieldMessage;
import com.vlemos.cursomc.services.validation.utis.BR;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

/**
 *
 * @author Vinicius Lemos
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{
    
    @Autowired
    private ClienteRepository repo;
    
    @Autowired
    private HttpServletRequest request;
    
    @Override
    public void initialize(ClienteUpdate ann){
        
    }
    
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context){
        // para buscar os atributos da URI recebida 
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));
        
        List<FieldMessage> list = new ArrayList<>();
        
     
         
         Cliente aux = repo.findByEmail(objDto.getEmail());
         if(aux != null && !aux.getId().equals(uriId)){
              list.add(new FieldMessage("email","Email Já existente"));
         }
        
        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
