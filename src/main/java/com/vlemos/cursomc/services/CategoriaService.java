/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlemos.cursomc.services;

import com.vlemos.cursomc.domain.Categoria;
import com.vlemos.cursomc.repositories.CategoriaRepository;
import com.vlemos.cursomc.services.expections.ObjectNotFoundException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Vinicius Lemos
 */
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repo;
    
    public Categoria buscar(Integer id) {
        Optional<Categoria> obj;
        obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                  "Objeto Não encontrado! Id: " + id + " Tipo " + Categoria.class.getName()));
        
    }

}
