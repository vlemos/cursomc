/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services.validation;

import com.vlemos.cursomc.domain.enums.TipoCliente;
import com.vlemos.cursomc.dto.ClienteNewDTO;
import com.vlemos.cursomc.resources.expections.FieldMessage;
import com.vlemos.cursomc.services.validation.utis.BR;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Vinicius Lemos
 */
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{
    
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
        
        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
    
}
