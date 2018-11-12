/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Cliente;
import com.vlemos.cursomc.repositories.ClienteRepository;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vinicius Lemos
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;
    
    public Cliente find(Integer id) {
        Optional<Cliente> obj;
        obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto Não encontrado! Id: " + id + " Tipo " + Cliente.class.getName()));
        
    }

}
