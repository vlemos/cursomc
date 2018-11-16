/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services.validation;

import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.dto.ClienteNewDTO;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.resources.expections.FieldMessage;
import com.vlemos.cursomc.services.validation.utis.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vinicius Lemos
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
    
    @Autowired
    private ClienteRepository repo;
    
    @Override
    public void initialize(ClienteInsert ann){
        
    }
    
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();
        
        // logica customizada para validação
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()) ){
            list.add(new FieldMessage("cpfOuCnpj","CPF Inválido"));
        }
        
         if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()) ){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido"));
        }
         
         Cliente aux = repo.findByEmail(objDto.getEmail());
         if(aux != null){
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
