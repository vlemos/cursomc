/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Estado;
import com.vlemos.cursomc.repositories.EstadoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vinicius.lemos
 */

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository repo;
    
    public List<Estado> findAll(){
        
        return repo.findAllByOrderByNome();
    }
    
}
